package com.thesis.sofen.entities.Promotion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Hotel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "promotion_id")
    private Long id;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    private List<PromotionDetail> promotionDetails;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "promotions_hotels",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    @JsonIgnore
    private List<Hotel> hotels;
}
