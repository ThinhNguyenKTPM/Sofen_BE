package com.thesis.sofen.request.hotelService;

import com.thesis.sofen.dto.DetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class HotelServiceRequest {
    private String name;
    private Long price;
    private List<DetailDTO> serviceDetails;
}
