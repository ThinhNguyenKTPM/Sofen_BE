package com.thesis.sofen.response.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("iat")
    private LocalDateTime iat;

    @JsonProperty("ext")
    private LocalDateTime ext;
}
