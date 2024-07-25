package com.thesis.sofen.response.External;

import com.thesis.sofen.dto.Interface.IDetail;
import com.thesis.sofen.entities.RoomImage;
import lombok.Data;

import java.util.List;

@Data
public class RoomDetailResponse {
    private Long id;
    private Integer remain;
    private String roomTypeName;
    private Integer  area;
    private String bed;
    private Integer maxChildren;
    private Integer maxAdult;
    private Long price;
    private String description;
    private List<RoomImage> images;
    private List<IDetail> furnitures;
}
