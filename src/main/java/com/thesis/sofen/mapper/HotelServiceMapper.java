package com.thesis.sofen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelServiceMapper {
    HotelServiceMapper INSTANCE = Mappers.getMapper(HotelServiceMapper.class);

//    default HotelService toEntity(List<HotelServiceRequest> request) {
//        HotelService result = new HotelService();
//        List<JsonData> name = request.stream().map(HotelServiceRequest::getName).toList();
//        result.setName(name);
//        List<JsonData> description = request.stream().map(HotelServiceRequest::getDescription).toList();
//        result.setDescription(description);
//        return result;
//    }
}

