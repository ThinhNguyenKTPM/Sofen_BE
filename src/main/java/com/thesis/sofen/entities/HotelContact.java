package com.thesis.sofen.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "hotel_contacts")
@Data
public class HotelContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_contact_id")
    private Long id;

    @Column(name = "phone_number_code")
    private String phoneNumberCode;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

}
