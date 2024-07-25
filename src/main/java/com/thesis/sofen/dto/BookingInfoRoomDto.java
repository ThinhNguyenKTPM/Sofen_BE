package com.thesis.sofen.dto;

import lombok.Data;

@Data
public class BookingInfoRoomDto {
    private String name;
    private Integer adult;
    private String price;
    private Integer amount;
    private String total;
    private String imageUrl;
}
