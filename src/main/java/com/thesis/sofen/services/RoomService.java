package com.thesis.sofen.services;

import com.thesis.sofen.entities.Furniture.RoomFurniture;
import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.entities.Room;
import com.thesis.sofen.entities.RoomImage;
import com.thesis.sofen.entities.RoomType.RoomType;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.HotelRepository;
import com.thesis.sofen.repositories.RoomFurnitureRepository;
import com.thesis.sofen.repositories.RoomRepository;
import com.thesis.sofen.repositories.RoomTypeRepository;
import com.thesis.sofen.request.RoomImageRequest;
import com.thesis.sofen.request.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepo;
    private final HotelRepository hotelRepo;
    private final RoomFurnitureRepository roomFurnitureRepo;

    private final RoomTypeRepository roomTypeRepo;
    private final RoomImageService roomImageService;

    /**
     * Create a new room of a hotel
     */
    public Room create(RoomRequest request)
            throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(request.getHotelId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("hotel_not_found","Không thể tìm thấy Khách sạn với id: " + request.getHotelId()));
        RoomType roomType = roomTypeRepo.findById(request.getRoomTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("room_type_not_found","Không thể tìm thấy Loại phòng với id: " + request.getRoomTypeId()));

        List<RoomFurniture> roomFurnitures = new ArrayList<>();
        for(Long roomFurnitureId : request.getRoomFurnitureIds()){
            RoomFurniture roomFurniture = roomFurnitureRepo.findById(roomFurnitureId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("room_furniture_not_found","Không thể tìm thấy Nội thất phòng với id: " + roomFurnitureId));
            roomFurnitures.add(roomFurniture);
        }
        List<RoomImage> images = new ArrayList<>();
        for(RoomImageRequest imageUrl : request.getRoomImages()){
            RoomImage roomImage = roomImageService.create(imageUrl);
            images.add(roomImage);
        }

        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setRoomFurnitures(roomFurnitures);
        room.setRoomImages(images);
        room.setAmount(request.getAmount());
        room.setQuantity_remaining(request.getAmount());
        return roomRepo.save(room);

    }

    public List<Room> getAllHotelRoom(UUID hotelId) {
        if(hotelId == null){
            return roomRepo.findAll();
        }
        return roomRepo.findByHotelId(hotelId);
    }

    public Object update(Long id, Room room) {
        Room oldRoom = roomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("room_not_found", "Không thể tìm thấy phòng với id: " + id));
        oldRoom.setAmount(room.getAmount());
        oldRoom.setRoomImages(room.getRoomImages());
        oldRoom.setRoomFurnitures(room.getRoomFurnitures());

        return roomRepo.save(oldRoom);
    }
}
