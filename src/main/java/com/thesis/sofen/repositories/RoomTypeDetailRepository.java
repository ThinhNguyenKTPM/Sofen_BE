package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.RoomType.RoomTypeDetail;
import com.thesis.sofen.entities.RoomType.RoomTypeDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeDetailRepository extends JpaRepository<RoomTypeDetail, RoomTypeDetailID> {

    boolean existsByNameAndLanguageId(String name, Integer id);
}
