package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelPolicy.HotelPolicyDetail;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicyDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelPolicyDetailRepository extends JpaRepository<HotelPolicyDetail, HotelPolicyDetailID> {

    @Query("SELECT hpd FROM HotelPolicyDetail hpd WHERE hpd.name = :name AND hpd.language.id = :languageId")
    HotelPolicyDetail findByNameAndLanguageId(@Param("name") String name, @Param("languageId") int languageId);

    @Query("SELECT CASE WHEN COUNT(hpd) > 0 " +
            "THEN TRUE ELSE FALSE END " +
            "FROM HotelPolicyDetail hpd " +
            "WHERE hpd.name = :name AND hpd.language.id = :languageId AND hpd.hotelPolicy.id != :id")
    boolean existsByNameAndLanguageIdWithOtherId(
            @Param("name") String name,
            @Param("languageId") int id,
            @Param("id") Long policyId
    );
}
