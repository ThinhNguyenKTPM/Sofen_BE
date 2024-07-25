package com.thesis.sofen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "name")
    private String name;

    @Column(name = "other_person_name")
    private String otherPersonName;

    @Column(name = "is_women")
    private boolean isWomen;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "purpose")
    private String purpose;

}
