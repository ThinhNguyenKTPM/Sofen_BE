package com.thesis.sofen.mapper;

import com.thesis.sofen.entities.HotelImage;
import com.thesis.sofen.dto.ImageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelImageMapper {
    HotelImageMapper INSTANCE = Mappers.getMapper(HotelImageMapper.class);

    default HotelImage toEntity(ImageDTO request){
        HotelImage hotelImageEntity = new HotelImage();
        hotelImageEntity.setAlt(request.getAlt());
        hotelImageEntity.setIsMain(request.isMainImage());
        hotelImageEntity.setUrl(request.getUrl());
        return hotelImageEntity;
    }
}
