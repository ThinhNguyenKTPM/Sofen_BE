package com.thesis.sofen.request.roomType;

import com.thesis.sofen.entities.Language;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDetailRequest {
    private String langCode;

    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
}
