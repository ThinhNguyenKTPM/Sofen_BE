package com.thesis.sofen.entities.RoomType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table
@IdClass(RoomTypeDetailID.class)
public class RoomTypeDetail {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id")
    @JsonIgnore
    private RoomType roomType;



    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @Size(max = 50, message = "Name must be less than 50 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "bed")
    private String bed;


    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description")
    private String description;
}
