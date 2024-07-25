package com.thesis.sofen.request.roomType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeRequest {

    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private Long price;

    @Min(value = 1, message = "Max adults must be greater than 0")
    private int maxAdults;

    @Min(value = 1, message = "Max children must be greater than 0")
    private int maxChildren;

    @Min(value = 1, message = "Area must be greater than 0")
    private int area;

    private List<RoomTypeDetailRequest> roomTypeDetailRequests;

}
