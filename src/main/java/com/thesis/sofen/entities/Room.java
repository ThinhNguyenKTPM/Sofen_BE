package com.thesis.sofen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.entities.Furniture.RoomFurniture;
import com.thesis.sofen.entities.RoomType.RoomType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "rooms")
@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "quantity_remaining")
    private int quantity_remaining;

    @Enumerated(EnumType.STRING)
    private EUserStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    private Hotel hotel;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @Column(name = "room_images")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private List<RoomImage> roomImages;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade =
                    {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "room_furnitures_rooms",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "room_furniture_id")
    )
    private List<RoomFurniture> roomFurnitures;

    @ManyToMany(mappedBy = "rooms")
    @JsonIgnore
    private List<BookingDetail> bookingDetails;
}
