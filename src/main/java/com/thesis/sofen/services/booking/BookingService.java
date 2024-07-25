package com.thesis.sofen.services.booking;

import com.thesis.sofen.common.EBookingStatus;
import com.thesis.sofen.dto.BookingInfoDto;
import com.thesis.sofen.dto.BookingInfoRoomDto;
import com.thesis.sofen.entities.*;
import com.thesis.sofen.entities.UserRanking.UserRanking;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.*;
import com.thesis.sofen.request.booking.BookingPaymentRequest;
import com.thesis.sofen.request.booking.BookingRequest;
import com.thesis.sofen.request.booking.BookingRoomDto;
import com.thesis.sofen.request.hotel.SearchHotelRequest;
import com.thesis.sofen.request.payment.PaymentRequest;
import com.thesis.sofen.response.BookingHistoryResponse;
import com.thesis.sofen.response.External.RoomDetailResponse;
import com.thesis.sofen.response.PaymentUrlResponse;
import com.thesis.sofen.response.SuccessResponse;
import com.thesis.sofen.services.EmailService;
import com.thesis.sofen.services.PaymentService;
import com.thesis.sofen.services.external.HotelExternalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingDetailRepository bookingDetailRepo;
    private final HotelExternalService hotelExternalService;
    private final PaymentService paymentService;
    private final BookingRepository bookingRepo;
    private final GuestRepository guestRepo;
    private final UserRepository userRepo;
    private final RoomRepository roomRepo;
    private final PaymentRepository paymentRepo;
    private final EmailService emailService;

    @Transactional
    public PaymentUrlResponse createBooking(BookingRequest request, String lang)
            throws ResourceNotFoundException, UnsupportedEncodingException {
        //Check room available
        checkRoomAvailable(request, lang);

        //Create booking
        User user = null;
        if (request.getUserId() != null) {
            user = userRepo.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("user_not_found", request.getUserId().toString()));
        }

        LocalDateTime now = LocalDateTime.now();
        Guest guest = createGuest(request);
        List<BookingDetail> bookingDetails = createBookingDetail(request);
        int totalDays = calculateTotalDays(request.getCheckIn(), request.getCheckOut());
        Booking booking = Booking.builder()
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .adultAmount(request.getAdult())
                .childrenAmount(request.getChildren())
                .pickUpLocation(request.getPickUpLocation())
                .specialRequest(request.getSpecialRequest())
                .createdAt(now)
                .updatedAt(now)
                .guest(guest)
                .user(user)
                .totalPrice(bookingDetails.stream().mapToLong(detail -> detail.getPrice() * detail.getAmount()).sum() * totalDays)
                .bookingDetails(bookingDetails)
                .bookingStatus(EBookingStatus.PENDING)
                .build();
        bookingRepo.save(booking);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .bookingId(booking.getId())
                .amount(booking.getTotalPrice())
                .title("Booking payment")
                .build();
        return paymentService.createPaymentUrl(paymentRequest);
    }

    private List<BookingDetail> createBookingDetail(BookingRequest request) throws ResourceNotFoundException {
        List<BookingDetail> bookingDetails = new ArrayList<>();
        List<BookingRoomDto> bookingRoomDtos = request.getRooms();
        for (BookingRoomDto room : bookingRoomDtos) {
            Room roomEntity = roomRepo.findById(room.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("room_not_found", room.getId().toString()));
            BookingDetail bookingDetail = BookingDetail.builder()
                    .price(room.getPrice())
                    .amount(room.getAmount())
                    .rooms(Collections.singletonList(roomEntity))
                    .build();
            bookingDetailRepo.save(bookingDetail);
            bookingDetails.add(bookingDetail);
        }
        return bookingDetails;
    }

    private Guest createGuest(BookingRequest request) {
//        Guest guest = Guest.builder()
//                .name(request.getName())
//                .otherPersonName(request.getOtherPersonName())
//                .phone(request.getPhone())
//                .phoneCode(request.getPhoneCode())
//                .email(request.getEmail())
//                .build();
        Guest guest = new Guest();
        guest.setName(request.getName());
        guest.setOtherPersonName(request.getOtherPersonName());
        guest.setPhone(request.getPhone());
        guest.setPhoneCode(request.getPhoneCode());
        guest.setEmail(request.getEmail());
        return guestRepo.save(guest);
    }

    private void checkRoomAvailable(BookingRequest request, String lang) throws ResourceNotFoundException {
        SearchHotelRequest search = SearchHotelRequest.builder()
                .hotelId(request.getHotelId())
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .adult(request.getAdult())
                .children(request.getChildren())
                .build();
        List<RoomDetailResponse> roomList = hotelExternalService.filter(search, lang);
        List<BookingRoomDto> bookingRoomDtos = request.getRooms();
        for (BookingRoomDto room : bookingRoomDtos) {
            //Check room available
            if (roomList.stream().noneMatch(r -> r.getId().equals(room.getId()) || r.getRemain() >= room.getAmount())) {
                throw new ResourceNotFoundException("room_not_available", room.getId().toString());
            }
        }
    }

    public SuccessResponse paymentReturn(BookingPaymentRequest req) throws ResourceNotFoundException {
        String vnp_TxnRef = req.getTxnRef();
        UUID bookingID = UUID.fromString(vnp_TxnRef);
        Booking booking = bookingRepo.findById(bookingID)
                .orElseThrow(() -> new ResourceNotFoundException("booking_not_found", bookingID.toString()));
        if (req.getResponseCode().equals("00")) {
            processPaymentSuccess(booking, req);
            processSendEmail(booking);
        } else if (req.getResponseCode().equals("24")) {
            booking.setBookingStatus(EBookingStatus.CANCEL);
            bookingRepo.save(booking);
            return new SuccessResponse("error");
        }

        return new SuccessResponse(booking.getGuest().getEmail());
    }

    private void processPaymentSuccess(Booking booking, BookingPaymentRequest req) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(req.getPayDate(), formatter);
        booking.setUpdatedAt(dateTime);
        booking.setBookingStatus(EBookingStatus.SUCCESS);
        User user = booking.getUser();
        if(user != null){
            UserRanking userRanking = user.getUserRanking();
            user.setPoint(user.getPoint() + booking.getTotalPrice().intValue()*userRanking.getRewardPercent()/100000);
        }
        Payment payment = Payment.builder()
                .bankCode(req.getBankCode())
                .transactionNo(req.getTransactionNo())
                .cardType(req.getCardType())
                .paymentDate(dateTime)
                .build();
        paymentRepo.save(payment);
        booking.setPayment(payment);
        bookingRepo.save(booking);
    }

    private void processSendEmail(Booking booking) {
        String hotelName = booking.getBookingDetails().get(0).getRooms().get(0).getHotel().getName();
        String email = booking.getGuest().getEmail();
        BookingInfoDto bookingInfo = new BookingInfoDto();
        bookingInfo.setFinalPrice(addCommas(booking.getTotalPrice().toString()) + " VND");
        bookingInfo.setTax("Tax Inclusive");
        bookingInfo.setHotelName(hotelName);
        bookingInfo.setPeriodStay(formatDate(booking.getCheckIn()) + " - " + formatDate(booking.getCheckOut()));

        bookingInfo.setSubPrice(addCommas(String.valueOf(booking.getTotalPrice())) + " VND");
        bookingInfo.setRooms(getBookingInfoRoomDtos(booking));
        emailService.sendBookingSuccessEmail(email, bookingInfo);
    }

    private List<BookingInfoRoomDto> getBookingInfoRoomDtos(Booking booking) {
        List<BookingInfoRoomDto> rooms = new ArrayList<>();
        for (BookingDetail detail : booking.getBookingDetails()) {
            BookingInfoRoomDto roomInfo = new BookingInfoRoomDto();
            roomInfo.setAmount(detail.getAmount());
            roomInfo.setPrice(addCommas(detail.getPrice().toString()));
            roomInfo.setName(detail.getRooms().get(0).getRoomType().getName());
            roomInfo.setAdult(detail.getRooms().get(0).getRoomType().getMaxAdults());
            roomInfo.setImageUrl(detail.getRooms().get(0).getRoomImages().get(0).getUrl());
            roomInfo.setTotal(addCommas(String.valueOf(detail.getPrice() * detail.getAmount())) + " VND");
            rooms.add(roomInfo);
        }
        return rooms;
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMMM d'st', yyyy", Locale.ENGLISH));
    }

    public String addCommas(String numberString) {
        long number = Long.parseLong(numberString);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }


    public Page<BookingHistoryResponse> getBookingHistoryByUser(UUID userId, Pageable pageable, String status) {
        if (status == null || "ALL".equals(status)) {
            var result = bookingRepo.findByUserId(userId, pageable);
            return mappingBookingInfo(result);

        }
        var result = bookingRepo.findByUserIdAndBookingStatus(userId, EBookingStatus.valueOf(status), pageable);
        return mappingBookingInfo(result);

    }

    public Page<BookingHistoryResponse> mappingBookingInfo(Page<Booking> bookings) {
        return bookings.map(booking -> {
            BookingHistoryResponse bookingHistoryResponse = new BookingHistoryResponse();
            bookingHistoryResponse.setHotelId(booking.getBookingDetails().get(0).getRooms().get(0).getHotel().getId());
            bookingHistoryResponse.setBookingId(booking.getId());
            UUID userId = booking.getUser() == null ? null : booking.getUser().getId();
            bookingHistoryResponse.setUserId(userId);
            bookingHistoryResponse.setEmail(booking.getGuest().getEmail());
            bookingHistoryResponse.setFullName(booking.getGuest().getName());
            bookingHistoryResponse.setPhoneNumber(booking.getGuest().getPhone());
            bookingHistoryResponse.setPhoneCode(booking.getGuest().getPhoneCode());
            bookingHistoryResponse.setBookingDate(booking.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss ")));
            bookingHistoryResponse.setCheckIn(booking.getCheckIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            bookingHistoryResponse.setCheckOut(booking.getCheckOut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            bookingHistoryResponse.setHotelName(booking.getBookingDetails().get(0).getRooms().get(0).getHotel().getName());
            bookingHistoryResponse.setStatus(booking.getBookingStatus().toString());
            bookingHistoryResponse.setTotalPrices(booking.getTotalPrice().toString());
            bookingHistoryResponse.setSpecialRequest(booking.getSpecialRequest());
            bookingHistoryResponse.setPickUpLocation(booking.getPickUpLocation());
            List<BookingInfoRoomDto> bookingDetails = new ArrayList<>();
            for (BookingDetail bookingDetail : booking.getBookingDetails()) {
                if(bookingDetail == null) continue;
                BookingInfoRoomDto bookingInfoRoomDto = getBookingInfoRoomDto(bookingDetail);
                bookingDetails.add(bookingInfoRoomDto);
            }
            bookingHistoryResponse.setRoomBookingInfos(bookingDetails);
            return bookingHistoryResponse;
        });
    }

    @NotNull
    private static BookingInfoRoomDto getBookingInfoRoomDto(BookingDetail bookingDetail) {
        BookingInfoRoomDto bookingInfoRoomDto = new BookingInfoRoomDto();
        bookingInfoRoomDto.setAmount(bookingDetail.getAmount());
        Long price = bookingDetail.getRooms().get(0).getRoomType().getPrice();
        bookingInfoRoomDto.setPrice(price.toString());
        bookingInfoRoomDto.setName(bookingDetail.getRooms().get(0).getRoomType().getName());
        bookingInfoRoomDto.setAdult(bookingDetail.getRooms().get(0).getRoomType().getMaxAdults());
        bookingInfoRoomDto.setImageUrl(bookingDetail.getRooms().get(0).getRoomImages().get(0).getUrl());
        long total = price * bookingDetail.getAmount();
        bookingInfoRoomDto.setTotal(Long.toString(total));
        return bookingInfoRoomDto;
    }

    public int calculateTotalDays(LocalDate checkIn, LocalDate checkOut) {
        Period period = Period.between(checkIn, checkOut);
        return period.getDays();
    }
}


