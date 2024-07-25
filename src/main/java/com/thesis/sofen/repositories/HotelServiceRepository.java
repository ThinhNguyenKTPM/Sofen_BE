package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelService.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelServiceRepository  extends JpaRepository<HotelService, Long> {

    boolean existsByName(String name);

    @Query("SELECT hs FROM Hotel h inner join h.services hs WHERE h.id = :hotelId")
    List<HotelService> findAllByHotelId(UUID hotelId);
}
