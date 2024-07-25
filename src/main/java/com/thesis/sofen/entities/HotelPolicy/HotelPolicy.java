package com.thesis.sofen.entities.HotelPolicy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.common.EUserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "hotel_policies")
public class HotelPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_policy_id")
    private Long id;

    @Size(max = 50, message = "Policy.json name must be less than 50 characters")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private EUserStatus status;

    @OneToMany(mappedBy = "hotelPolicy", fetch = FetchType.EAGER)
    private List<HotelPolicyDetail> policyDetails;
}
