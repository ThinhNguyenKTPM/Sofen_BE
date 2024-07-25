package com.thesis.sofen.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "room_images")
@Data
@Entity
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "alt")
    private String alt;

    @Column(name = "url")
    private String url;

    @Column(name = "is_main")
    private Boolean isMain;

    @Column(name = "status")
    private String status;
}
