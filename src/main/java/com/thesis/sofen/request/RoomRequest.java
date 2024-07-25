package com.thesis.sofen.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoomRequest {
    private Integer amount;
    private UUID hotelId;
    private Long roomTypeId;
    private List<Long> roomFurnitureIds;
    private List<RoomImageRequest> roomImages;
}
