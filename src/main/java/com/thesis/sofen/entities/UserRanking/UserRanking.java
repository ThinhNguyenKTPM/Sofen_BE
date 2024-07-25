package com.thesis.sofen.entities.UserRanking;

import com.thesis.sofen.common.EUserRanking;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_ranking")
public class UserRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_ranking_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private EUserRanking name;

    @Column(name = "reward_percent")
    private int rewardPercent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userRanking")
    private List<UserRankingDetail> userRankingDetails;

}
