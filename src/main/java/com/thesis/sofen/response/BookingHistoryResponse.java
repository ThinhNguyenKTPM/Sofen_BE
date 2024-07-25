package com.thesis.sofen.response;

import com.thesis.sofen.dto.BookingInfoRoomDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BookingHistoryResponse {
    private UUID hotelId;
    private UUID bookingId;
    private UUID userId;
    private String hotelName;
    private String checkIn;
    private String checkOut;
    private String status;
    private String totalPrices;
    private String bookingDate;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String phoneCode;
    private String specialRequest;
    private String pickUpLocation;
    private List<BookingInfoRoomDto> roomBookingInfos;
}
