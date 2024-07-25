package com.thesis.sofen.services.external;

import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.HotelRepository;
import com.thesis.sofen.repositories.RoomRepository;
import com.thesis.sofen.request.hotel.SearchHotelRequest;
import com.thesis.sofen.response.External.RoomDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomExternalService {
    private final RoomRepository roomRepo;
    private final HotelRepository hotelRepo;


    public List<RoomDetailResponse> getRoomDetail(UUID hotelId, String lang, SearchHotelRequest query) throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("hotel_not_found", "Hotel not found with id " + hotelId.toString()
        ));
//        Object object = roomRepo.findByQuery(hotelId, query);
        return null;
    }
}
