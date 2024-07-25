package com.thesis.sofen.mapper;

import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.request.hotel.CreateHotelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    default Hotel toEntity(CreateHotelRequest request) {
        Hotel result = new Hotel();
        result.setName(request.getName());
        result.setAddressDetail(request.getAddressDetail());
//        result.setLatitude(request.getLatitude());
//        result.setLongitude(request.getLongitude());
//        result.setDescription(request.getDescription());
        return result;
    }
}
