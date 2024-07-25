package com.thesis.sofen.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelAddressRequest {
    @NotBlank
    private String ward;
    @NotBlank
    private String district;
    @NotBlank
    private String province;
    @NotBlank
    private String nation;
}
