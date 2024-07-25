package com.thesis.sofen.mapper;

import com.thesis.sofen.common.ETokenType;
import com.thesis.sofen.entities.Token;
import com.thesis.sofen.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TokenMapper {
    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);

    default Token toEntity(User user, String token, ETokenType tokenType, Long expirationMs) {
        return Token.builder()
                .user(user)
                .token(token)
                .tokenType(tokenType)
                .expiredAt(LocalDateTime.now().plus(expirationMs, ChronoUnit.MILLIS))
                .createdAt(LocalDateTime.now())
                .isRevoked(false)
                .build();
    }
}
