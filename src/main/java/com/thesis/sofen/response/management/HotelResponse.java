package com.thesis.sofen.response.management;

import com.thesis.sofen.entities.HotelAddress;
import com.thesis.sofen.entities.HotelContact;
import lombok.Data;

@Data
public class HotelResponse {
    private String id;
    private String name;
    private String addressDetail;
    private String status;
    private HotelContact hotelContact;
    private HotelAddress hotelAddress;
}
