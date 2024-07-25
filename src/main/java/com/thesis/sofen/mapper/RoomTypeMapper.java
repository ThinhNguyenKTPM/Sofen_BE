package com.thesis.sofen.mapper;

import com.thesis.sofen.entities.RoomType.RoomType;
import com.thesis.sofen.request.roomType.RoomTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface RoomTypeMapper {
    RoomTypeMapper INSTANCE = Mappers.getMapper(RoomTypeMapper.class);
    default RoomType toEntity(RoomTypeRequest request) {
        RoomType result = new RoomType();
        result.setName(request.getName());
        result.setPrice(request.getPrice());
        result.setMaxAdults(request.getMaxAdults());
        result.setMaxChildren(request.getMaxChildren());
        result.setArea(request.getArea());
        return result;
    }
}
