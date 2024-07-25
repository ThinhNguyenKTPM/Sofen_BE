package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.Furniture.FurnitureDetail;
import com.thesis.sofen.entities.Furniture.FurnitureDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFurnitureDetailRepository extends JpaRepository<FurnitureDetail, FurnitureDetailID> {

    boolean existsByNameAndLanguageId(String name, int languageId);
}


