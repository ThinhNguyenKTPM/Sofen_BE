package com.thesis.sofen.repositories;

import com.thesis.sofen.dto.Interface.IDetail;
import com.thesis.sofen.entities.Furniture.RoomFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomFurnitureRepository extends JpaRepository<RoomFurniture, Long> {
    boolean existsByName(String name);
    @Query("""
            SELECT fd.name AS name, fd.description AS description
            FROM Room r INNER JOIN r.roomFurnitures rf INNER JOIN rf.furnitureDetails fd
            WHERE r.id = :id AND fd.language.code = :lang
                """)
    List<IDetail> getDetailByRoomIdAndLang(Long id, String lang);

    @Query("""
            SELECT rf
            FROM Room r INNER JOIN r.roomFurnitures rf INNER JOIN rf.furnitureDetails fd
            WHERE r.id = :id
                """)
    List<RoomFurniture> findByRoomId(Long id);
}
