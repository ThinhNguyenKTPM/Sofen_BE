package com.thesis.sofen.response.HotelPolicy;

import com.thesis.sofen.dto.DetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class HotelPolicyResponse {
    private Long id;
    private String name;
    private List<DetailDTO> hotelPolicyDetails;
}
