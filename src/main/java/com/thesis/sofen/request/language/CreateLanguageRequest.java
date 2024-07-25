package com.thesis.sofen.request.language;

import lombok.Data;

@Data
public class CreateLanguageRequest {
    private String name;
    private String code;
    private boolean isDefault;
}
