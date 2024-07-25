package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelPolicy.HotelPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HotelPolicyRepository extends JpaRepository<HotelPolicy, Long> {
    HotelPolicy findByName(String name);
    @Query(value = "SELECT CASE WHEN COUNT(hp) > 0 THEN TRUE ELSE FALSE END FROM HotelPolicy hp WHERE hp.name = :name AND hp.id != :id")
    boolean existsByNameWithOtherId(@Param("id") Long id, @Param("name")String name);

    @Query("SELECT hp FROM Hotel h inner join h.policies hp WHERE h.id = :hotelId")
    List<HotelPolicy> findAllByHotelId(UUID hotelId);
}
