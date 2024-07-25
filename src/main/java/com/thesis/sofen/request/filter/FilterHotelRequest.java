package com.thesis.sofen.request.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class FilterHotelRequest extends PageAndFilter {
    @JsonProperty("status")
    private String status;
    private UUID hotelId;
}
