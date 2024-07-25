package com.thesis.sofen.entities.RoomType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "room_types")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_type_id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "max_adults")
    private int maxAdults;

    @Column(name = "max_children")
    private int maxChildren;

    @Column(name = "area")
    private int area;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "roomType")
    private List<RoomTypeDetail> roomTypeDetails;
}
