package com.thesis.sofen.entities.Furniture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "room_furnitures")
public class RoomFurniture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_furniture_id")
    private Long id;

    @Size(max = 50, message = "Name must be less than 50 characters")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "roomFurniture", fetch = FetchType.EAGER)
    private List<FurnitureDetail> furnitureDetails;
}
