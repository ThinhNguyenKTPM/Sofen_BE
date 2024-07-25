package com.thesis.sofen.services;

import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.entities.HotelImage;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.mapper.HotelImageMapper;
import com.thesis.sofen.repositories.HotelImageRepository;
import com.thesis.sofen.repositories.HotelRepository;
import com.thesis.sofen.dto.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelImageServices {
    private final HotelImageRepository hotelImageRepo;
    private final HotelRepository hotelRepo;
    public ResponseEntity<?> createImage(UUID hotelId, ImageDTO request) throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);

        if(hotel == null){
            throw new ResourceNotFoundException("hotel_not_found", "Không tìm thấy khách sạn với id: " + hotelId);
        }
        HotelImage hotelImage = HotelImageMapper.INSTANCE.toEntity(request);

        hotelImageRepo.save(hotelImage);
        List<HotelImage> images = hotel.getImages();
        if(request.isMainImage()){
            for(HotelImage image: images){
                if(image.getIsMain()){
                    image.setIsMain(false);
                }
            }
            hotel.setImages(images);
        }

        images.add(hotelImage);
        hotel.setImages(images);
        hotelRepo.save(hotel);
        return ResponseEntity.ok(hotelImage);
    }

}
