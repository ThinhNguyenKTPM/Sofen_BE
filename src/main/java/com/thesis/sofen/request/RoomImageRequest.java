package com.thesis.sofen.request;

import lombok.Data;

@Data
public class RoomImageRequest {
    private String url;
    private String alt;
    private Boolean isMain;
}
