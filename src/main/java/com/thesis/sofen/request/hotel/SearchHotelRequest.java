package com.thesis.sofen.request.hotel;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class SearchHotelRequest {
    private UUID hotelId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer adult;
    private Integer children;
    private Long fromPrice;
    private Long toPrice;
    private Integer roomAmount;
}
