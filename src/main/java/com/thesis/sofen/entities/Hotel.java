package com.thesis.sofen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.entities.HotelInfo.HotelInfo;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicy;
import com.thesis.sofen.entities.HotelService.HotelService;
import com.thesis.sofen.entities.Promotion.Promotion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Table(name = "hotels")
@Data
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private UUID id;

    @Column(name = "name", unique = true)
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

//    @Column(name = "description")
//    @NotBlank(message = "Description is required")
//    @Size(max = 1000, message = "Description must be less than 1000 characters")
//    private String description;

    @Column(name = "address_detail")
    @NotBlank(message = "Address detail is required")
    @Size(max = 100, message = "Address detail must be less than 100 characters")
    private String addressDetail;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EUserStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "hotel_address_id", referencedColumnName = "hotel_address_id")
    private HotelAddress hotelAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "hotel_contact_id", referencedColumnName = "hotel_contact_id")
    private HotelContact hotelContact;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "hotels"
    )
    @JsonIgnore
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "hotels"
    )
    @JsonIgnore
    private List<Promotion> promotions;

    @OneToMany(fetch = FetchType.EAGER,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "hotel_id")
    private List<HotelImage> images;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "hotels_hotel_policies",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_policy_id")
    )
    @JsonIgnore
    private List<HotelPolicy> policies;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "hotels_hotel_services",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_service_id")
    )
    @JsonIgnore
    private List<HotelService> services;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private List<Room> rooms;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel")
    private List<HotelInfo> hotelInfos;
}
