package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelImageRepository  extends JpaRepository<HotelImage, Long>{

}
