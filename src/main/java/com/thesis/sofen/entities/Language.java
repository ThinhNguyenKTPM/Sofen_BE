package com.thesis.sofen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer id;

    @Size(max = 50, message = "Language name must be less than 50 characters")
    private String name;

    @Size(max = 2, min = 2, message = "Language code must be 2 characters long")
    private String code;

    @Column(name = "is_default")
    private boolean isDefault;
}
