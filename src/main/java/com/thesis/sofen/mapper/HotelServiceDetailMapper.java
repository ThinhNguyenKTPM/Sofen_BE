package com.thesis.sofen.mapper;

import com.thesis.sofen.dto.DetailDTO;
import com.thesis.sofen.entities.HotelService.HotelService;
import com.thesis.sofen.entities.HotelService.HotelServiceDetail;
import com.thesis.sofen.entities.Language;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelServiceDetailMapper {
    HotelServiceDetailMapper INSTANCE = Mappers.getMapper(HotelServiceDetailMapper.class);

    default HotelServiceDetail toEntity(DetailDTO request, Language language, HotelService hotelService) {
        HotelServiceDetail result = new HotelServiceDetail();
        result.setName(request.getName());
        result.setDescription(request.getDescription());
        result.setHotelService(hotelService);
        result.setLanguage(language);
        return result;
    }
}
