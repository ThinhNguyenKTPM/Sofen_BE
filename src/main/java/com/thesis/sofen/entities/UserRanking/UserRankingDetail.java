package com.thesis.sofen.entities.UserRanking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.sofen.entities.Language;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_ranking_detail")
public class UserRankingDetail {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ranking_id")
    @JsonIgnore
    private UserRanking userRanking;
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    @JsonIgnore
    private Language language;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;
}
