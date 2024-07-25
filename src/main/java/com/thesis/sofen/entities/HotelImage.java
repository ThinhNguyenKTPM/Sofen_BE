package com.thesis.sofen.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "hotel_images")
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_image_id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "is_main")
    private Boolean isMain;

    @Column(name = "alt")
    private String alt;
}
