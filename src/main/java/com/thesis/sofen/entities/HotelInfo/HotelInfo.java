package com.thesis.sofen.entities.HotelInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.entities.Language;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "hotel_info")
@Data
@Entity
@IdClass(HotelInfoId.class)
public class HotelInfo {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Hotel hotel;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Language language;

    @Column(name = "description", length = 1500)
    private String description;
}
