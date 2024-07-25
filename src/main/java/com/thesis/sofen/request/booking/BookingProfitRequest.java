package com.thesis.sofen.request.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"time", "type", "from", "to", "hotelId"})
public class BookingProfitRequest implements Serializable {
    @JsonProperty("time")
    private String time;
    @JsonProperty("type")
    private String type;
    @JsonProperty("from")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime from;
    @JsonProperty("to")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime to;
    private UUID hotelId;
    @ConstructorProperties({"time", "type", "from", "to", "hotelId"})
    public BookingProfitRequest(String time, String type, LocalDateTime from, LocalDateTime to, UUID hotelId) {
        this.time = time;
        this.type = type;
        this.from = from;
        this.to = to;
        this.hotelId = hotelId;
    }
}
