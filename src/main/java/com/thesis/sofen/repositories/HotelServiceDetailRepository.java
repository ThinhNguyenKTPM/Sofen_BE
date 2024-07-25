package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelService.HotelServiceDetail;
import com.thesis.sofen.entities.HotelService.ServiceDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelServiceDetailRepository extends JpaRepository<HotelServiceDetail, ServiceDetailID> {
    @Query("SELECT CASE WHEN COUNT(hsd) > 0 " +
            "THEN TRUE ELSE FALSE END " +
            "FROM HotelServiceDetail hsd " +
            "WHERE hsd.name = :name AND hsd.language.code = :langCode")
    boolean existsByNameAndLangCode(String name, String langCode);
}
