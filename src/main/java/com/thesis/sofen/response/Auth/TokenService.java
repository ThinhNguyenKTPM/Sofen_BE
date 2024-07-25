package com.thesis.sofen.response.Auth;

import com.thesis.sofen.common.ETokenType;
import com.thesis.sofen.entities.Token;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.mapper.TokenMapper;
import com.thesis.sofen.repositories.TokenRepository;
import com.thesis.sofen.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
    @Value("${app.refresh_token_expiration_ms}")
    private Long ExpirationMs;

    private final UserRepository userRepo;
    private final TokenRepository tokenRepo;

    public Long getExpirationMs() {
        return ExpirationMs;
    }

    public RefreshTokenResponse createRefreshToken(String email) {
        Token refreshToken = TokenMapper.INSTANCE.toEntity(userRepo.findByEmail(email).get(),
                UUID.randomUUID().toString(), ETokenType.REFRESH_TOKEN, ExpirationMs);
        tokenRepo.save(refreshToken);

        return RefreshTokenResponse.builder()
                .id(refreshToken.getId())
                .refreshToken(refreshToken.getToken())
                .expiredAt(refreshToken.getExpiredAt())
                .build();
    }
    public Token update(Token dbItem) {
        String refreshToken = UUID.randomUUID().toString();

        dbItem.setToken(refreshToken);
        dbItem.setExpiredAt(LocalDateTime.now().plus(ExpirationMs, ChronoUnit.MILLIS));
        tokenRepo.save(dbItem);
        return dbItem;
    }

}
