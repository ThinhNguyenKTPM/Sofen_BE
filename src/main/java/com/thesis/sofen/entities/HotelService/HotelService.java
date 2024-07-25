package com.thesis.sofen.entities.HotelService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.common.EUserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "hotel_services")
public class HotelService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_service_id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "icon", length = 500)
    private String icon;

    @Enumerated(EnumType.STRING)
    private EUserStatus status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotelService")
    private List<HotelServiceDetail> hotelServiceDetails;
}
