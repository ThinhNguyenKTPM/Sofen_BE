package com.thesis.sofen.entities.HotelPolicy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "hotel_policy_details")
@IdClass(HotelPolicyDetailID.class)
public class HotelPolicyDetail {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_policy_id")
    @ToString.Exclude
    @JsonIgnore
    private HotelPolicy hotelPolicy;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    @ToString.Exclude
    @JsonIgnore
    private Language language;

    @Size(max = 50, message = "Name must be less than 50 characters")
    @Column(name = "name")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(name = "description")
    private String description;
}
