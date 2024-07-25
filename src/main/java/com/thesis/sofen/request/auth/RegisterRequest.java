package com.thesis.sofen.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email_pattern")
    private String email;
    @NotBlank
    private String phoneCode;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Length(min = 8, max = 50, message = "password_length")
    private String password;
}