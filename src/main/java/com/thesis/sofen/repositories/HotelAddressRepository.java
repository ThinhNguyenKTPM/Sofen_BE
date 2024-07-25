package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.HotelAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelAddressRepository extends JpaRepository<HotelAddress, Long> {
    @Query("SELECT DISTINCT ha.nation FROM HotelAddress ha")
    List<String> findAllNations();

    @Query("SELECT DISTINCT ha.province FROM HotelAddress ha WHERE ha.nation = :nation")
    List<String> findProvincesByNation(String nation);

    @Query("SELECT DISTINCT ha.district FROM HotelAddress ha WHERE ha.nation = :nation AND ha.province = :province")
    List<String> findDistrictsByNationAndProvince(String nation, String province);
}
