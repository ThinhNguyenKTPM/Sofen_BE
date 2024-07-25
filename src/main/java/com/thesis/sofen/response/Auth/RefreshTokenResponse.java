package com.thesis.sofen.response.Auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefreshTokenResponse {
    private Long id;
    private String refreshToken;
    private LocalDateTime expiredAt;
}