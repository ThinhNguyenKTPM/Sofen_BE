package com.thesis.sofen.request.RoomFurniture;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomFurnitureDetailDTO {
    private String language;
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
}