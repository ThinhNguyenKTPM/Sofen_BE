package com.thesis.sofen.request.hotelPolicy;

import com.thesis.sofen.dto.DetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class HotelPoliciesRequest {
    private String name;
    private List<DetailDTO> policyDetails;
}
