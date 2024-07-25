package com.thesis.sofen.request.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookingHistoryRequest extends PageAndFilter {
    @JsonProperty("status")
    private String status;
    @JsonProperty("type")
    private String type;
    private String hotelId;
    private LocalDate startDate;
    private LocalDate endDate;

}
