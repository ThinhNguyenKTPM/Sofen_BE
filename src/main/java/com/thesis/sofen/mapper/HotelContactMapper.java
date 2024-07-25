package com.thesis.sofen.mapper;

import com.thesis.sofen.entities.HotelContact;
import com.thesis.sofen.request.HotelContactRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelContactMapper {

    HotelContactMapper INSTANCE = Mappers.getMapper(HotelContactMapper.class);

    default HotelContact toEntity(HotelContactRequest request) {
        HotelContact result = new HotelContact();
        result.setPhoneNumberCode(request.getPhoneNumberCode());
        result.setPhoneNumber(request.getPhoneNumber());
        result.setEmail(request.getEmail());
        result.setFacebook(request.getFacebook());
        result.setInstagram(request.getInstagram());
        return result;
    }
}
