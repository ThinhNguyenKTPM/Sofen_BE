package com.thesis.sofen.mapper;

import com.thesis.sofen.dto.DetailDTO;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicy;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicyDetail;
import com.thesis.sofen.response.HotelPolicy.HotelPolicyResponse;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public interface HotelPolicyMapper {
    HotelPolicyMapper INSTANCE = Mappers.getMapper(HotelPolicyMapper.class);

    default HotelPolicyResponse toResponse(HotelPolicy hotelPolicy) {
        HotelPolicyResponse result = new HotelPolicyResponse();
        result.setId(hotelPolicy.getId());
        result.setName(hotelPolicy.getName());

        List<DetailDTO> details = new ArrayList<>();
        for (HotelPolicyDetail hotelPolicyDetail : hotelPolicy.getPolicyDetails()) {
            DetailDTO detail = HotelPolicyDetailMapper.INSTANCE.toResponse(hotelPolicyDetail);
            details.add(detail);
        }
        result.setHotelPolicyDetails(details);
        return result;
    }

}
