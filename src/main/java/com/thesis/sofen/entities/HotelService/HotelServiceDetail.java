package com.thesis.sofen.entities.HotelService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Language;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hotel_service_detail")
@IdClass(ServiceDetailID.class)
public class HotelServiceDetail {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_service_id")
    @JsonIgnore
    private HotelService hotelService;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

}
