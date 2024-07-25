package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelContactRepository extends JpaRepository<HotelContact, Long> {
}
