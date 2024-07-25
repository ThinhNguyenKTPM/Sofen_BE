package com.thesis.sofen.dto;

import lombok.Data;

@Data
public class ImageDTO {
    private boolean isMainImage;
    private String url;
    private String alt;
}
