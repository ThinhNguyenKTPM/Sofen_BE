package com.thesis.sofen.repositories;

import com.thesis.sofen.entities.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomImageRepository  extends JpaRepository<RoomImage, Long> {

}
