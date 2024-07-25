package com.thesis.sofen.request;


import lombok.Data;

@Data
public class HotelContactRequest {
    private String phoneNumberCode;
    private String phoneNumber;
    private String email;
    private String facebook;
    private String instagram;
}
