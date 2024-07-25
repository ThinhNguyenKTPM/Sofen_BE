package com.thesis.sofen.services;

import com.thesis.sofen.entities.RoomImage;
import com.thesis.sofen.repositories.RoomImageRepository;
import com.thesis.sofen.request.RoomImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomImageService {
    private final RoomImageRepository roomImageRepository;
    public RoomImage create(RoomImageRequest request){
        RoomImage roomImage = new RoomImage();
        roomImage.setUrl(request.getUrl());
        roomImage.setAlt(request.getAlt());
        roomImage.setIsMain(request.getIsMain());
        return roomImageRepository.save(roomImage);
    }
}
