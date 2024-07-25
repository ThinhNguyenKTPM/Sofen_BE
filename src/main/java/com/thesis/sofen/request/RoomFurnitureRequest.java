package com.thesis.sofen.request;

import com.thesis.sofen.dto.DetailDTO;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RoomFurnitureRequest {
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;
    private List<DetailDTO> furnitureDetails;
}



