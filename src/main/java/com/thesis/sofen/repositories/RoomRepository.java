package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.Room;
import com.thesis.sofen.request.hotel.SearchHotelRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId")
    List<Room> findByHotelId(UUID hotelId);


//    @Query("""
//
//            SELECT r
//            FROM Room r INNER JOIN r.roomType rt
//            WHERE r.hotel.id = :hotelId AND r.status = 'ACTIVE'
//            AND (:#{#query.fromPrice} IS NULL OR rt.price >= :#{#query.fromPrice})
//            AND (:#{#query.toPrice} IS NULL OR rt.price <= :#{#query.toPrice})
//
//            """)
//    List<Room> findByQuery(@Param("hotelID") UUID hotelId, SearchHotelRequest query);





}
