package com.thesis.sofen.services.management;

import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.dto.Interface.create.ICreateMonth;
import com.thesis.sofen.dto.Interface.create.ICreateWeek;
import com.thesis.sofen.dto.Interface.create.ICreateYear;
import com.thesis.sofen.repositories.BookingRepository;
import com.thesis.sofen.request.booking.BookingProfitRequest;
import com.thesis.sofen.request.filter.BookingHistoryRequest;
import com.thesis.sofen.response.BookingHistoryResponse;
import com.thesis.sofen.services.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingManagementService {
    private final BookingRepository bookingRepo;
    private final BookingService bookingService;

    public Object getBookingProfit(BookingProfitRequest request){
        Object response;
        if(request.getFrom() == null)
            request.setFrom(LocalDateTime.of(LocalDate.now().getYear(), 1, 1,0,0));
        return bookingRepo.getBookingProfitAllTime(request);
    }

    public Object getBookingCreated(BookingProfitRequest request){
        Object response;
//        if(request.getFrom() == null)
//            request.setFrom(LocalDateTime.of(LocalDate.now().getYear(), 1, 1,0,0));
        switch (request.getTime()){
            case "day":
                response = bookingRepo.countByDate(request);
                break;
            case "week":
                response = bookingRepo.countByWeek(request);
                break;
            case "month":
                response = bookingRepo.countByMonth(request);
                break;
            case "year":
                response = bookingRepo.countByYear(request);
                break;
            default:
                return null;
        }
        return response;
    }

    public Page<BookingHistoryResponse> getBooking(BookingHistoryRequest request, Pageable pageable) {
        EUserStatus status = null;
        try {
            status = EUserStatus.valueOf(request.getStatus());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status: " + request.getStatus());
        }
        var response = bookingRepo.filterBooking(request, pageable);

        return bookingService.mappingBookingInfo(response);
    }

    public Object getBookingProfitByTime(BookingProfitRequest request) {
        Object response;
        switch (request.getTime()){
            case "day":
                response = bookingRepo.getBookingProfitByDay(request);
                break;
            case "week":
                response = getProfitCountByWeek(request);
                break;
            case "month":
                response = getProfitCountByMonth(request);
                break;
            case "year":
                response = getProfitCountByYear(request);
                break;
            default:
                return null;
        }
        return response;
    }

    private Object getProfitCountByYear(BookingProfitRequest request) {
        List<ICreateYear> list = bookingRepo.getBookingProfitByYear(request);
        List<Object> response = new ArrayList<>();
        for (ICreateYear item : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("label", item.getYear());
            map.put("count", item.getCount());
            response.add(map);
        }
        return response;
    }
    private Object getProfitCountByMonth(BookingProfitRequest request) {
        List<ICreateMonth> list = bookingRepo.getBookingProfitByMonth(request);
        List<Object> response = new ArrayList<>();
        for (ICreateMonth item : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("label", item.getMonth() + "-" + item.getYear());
            map.put("count", item.getCount());
            response.add(map);
        }
        return response;
    }

    private Object getProfitCountByWeek(BookingProfitRequest request) {
        List<ICreateWeek> list = bookingRepo.getBookingProfitByWeek(request);
        List<Object> response = new ArrayList<>();
        for (ICreateWeek item : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("label", item.getWeek() + "-" + item.getYear());
            map.put("count", item.getCount());
            response.add(map);
        }
        return response;
    }
}
