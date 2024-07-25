package com.thesis.sofen.entities.Furniture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "room_furniture_details")
@IdClass(FurnitureDetailID.class)
public class FurnitureDetail {
    @Id
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "furniture_id")
    @JsonIgnore
    private RoomFurniture roomFurniture;

    @Id
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @Size(max = 50, message = "Name must be less than 50 characters")
    @Column(name = "name")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description")
    private String description;
}
