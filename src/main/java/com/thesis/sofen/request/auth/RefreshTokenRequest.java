package com.thesis.sofen.request.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    @NotBlank(message = "token_required")
    private String refreshToken;
}
