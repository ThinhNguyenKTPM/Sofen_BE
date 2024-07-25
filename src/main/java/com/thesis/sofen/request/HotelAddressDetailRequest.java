package com.thesis.sofen.request;


import lombok.Data;

@Data
public class HotelAddressDetailRequest {
    private String detail;
    private HotelAddressRequest address;
}
