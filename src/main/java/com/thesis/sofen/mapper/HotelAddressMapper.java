package com.thesis.sofen.mapper;

import com.thesis.sofen.entities.HotelAddress;
import com.thesis.sofen.request.HotelAddressRequest;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelAddressMapper {
    HotelAddressMapper INSTANCE = Mappers.getMapper(HotelAddressMapper.class);

    default HotelAddress toEntity(HotelAddressRequest request) {
        HotelAddress result = new HotelAddress();
        result.setWard(request.getWard());
        result.setDistrict(request.getDistrict());
        result.setProvince(request.getProvince());
        result.setNation(request.getNation());
        return result;
    }
}
