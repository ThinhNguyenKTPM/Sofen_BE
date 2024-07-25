package com.thesis.sofen.mapper;

import com.thesis.sofen.request.language.CreateLanguageRequest;
import com.thesis.sofen.entities.Language;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LanguageMapper {
    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    default Language toEntity(CreateLanguageRequest request) {
        Language result = new Language();
        result.setName(request.getName());
        result.setCode(request.getCode());
        result.setDefault(request.isDefault());
        return result;
    }
}
