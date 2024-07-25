package com.thesis.sofen.response.External.Hotel;

import com.thesis.sofen.dto.Interface.IServiceResponse;
import com.thesis.sofen.dto.Interface.IPolicyResponse;
import com.thesis.sofen.entities.HotelImage;
import lombok.Data;

import java.util.List;

@Data
public class HotelDetailResponse {
    private String hotelName;
    private String hotelDescription;
    private String hotelAddress;
    private Long fromPrice;
    List<HotelImage> images;
    private List<IServiceResponse> services;
    private List<IPolicyResponse> policies;
}
