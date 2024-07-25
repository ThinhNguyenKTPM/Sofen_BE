package com.thesis.sofen.mapper;

import com.thesis.sofen.entities.HotelPolicy.HotelPolicy;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicyDetail;
import com.thesis.sofen.entities.Language;
import com.thesis.sofen.dto.DetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelPolicyDetailMapper {
    HotelPolicyDetailMapper INSTANCE = Mappers.getMapper(HotelPolicyDetailMapper.class);

    default HotelPolicyDetail toEntity(DetailDTO request, Language language, HotelPolicy hotelPolicy) {
        HotelPolicyDetail result = new HotelPolicyDetail();
        result.setHotelPolicy(hotelPolicy);
        result.setLanguage(language);
        result.setName(request.getName());
        result.setDescription(request.getDescription());
        return result;
    }

    default DetailDTO toResponse(HotelPolicyDetail entity) {
        DetailDTO result = new DetailDTO();
        result.setLangCode(entity.getLanguage().getCode());
        result.setName(entity.getName());
        result.setDescription(entity.getDescription());
        return result;
    }

}
