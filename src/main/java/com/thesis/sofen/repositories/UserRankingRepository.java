package com.thesis.sofen.repositories;

import com.thesis.sofen.common.EUserRanking;
import com.thesis.sofen.entities.UserRanking.UserRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRankingRepository extends JpaRepository<UserRanking, Integer> {

    UserRanking findByName(EUserRanking name);
}
