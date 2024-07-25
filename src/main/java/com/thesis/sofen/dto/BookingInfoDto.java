package com.thesis.sofen.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingInfoDto {
    private String subPrice;
    private String tax;
    private String finalPrice;
    private String hotelName;
    private String periodStay;
    private List<BookingInfoRoomDto> rooms;
}
