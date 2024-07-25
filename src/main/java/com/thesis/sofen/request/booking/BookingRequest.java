package com.thesis.sofen.request.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class BookingRequest {
    private UUID userId;
    private UUID hotelId;
    private String name;
    private String email;
    private String phone;
    private String phoneCode;
    private String otherPersonName;
    private String specialRequest;
    private String pickUpLocation;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer adult;
    private Integer children;
    @NotNull
    private List<BookingRoomDto> rooms;
}
