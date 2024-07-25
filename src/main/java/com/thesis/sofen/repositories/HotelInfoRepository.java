package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelInfo.HotelInfo;
import com.thesis.sofen.entities.HotelInfo.HotelInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelInfoRepository extends JpaRepository<HotelInfo, HotelInfoId> {
}
