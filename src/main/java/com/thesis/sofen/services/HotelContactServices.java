package com.thesis.sofen.services;

import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.entities.HotelContact;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.HotelContactRepository;
import com.thesis.sofen.repositories.HotelRepository;
import com.thesis.sofen.request.HotelContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelContactServices {
    private final HotelContactRepository hotelContactRepo;
    private final HotelRepository hotelRepo;
    public Object update(UUID hotelId, HotelContactRequest request) throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(hotelId).orElse(null);
        if(hotel == null){
            throw new ResourceNotFoundException("hotel_not_found", "Không tìm thấy khách sạn với id: " + hotelId);
        }
        HotelContact hotelContact = hotel.getHotelContact();
        if(hotelContact == null){
            hotelContact = new HotelContact();
        }
        hotelContact.setEmail(request.getEmail());
        hotelContact.setFacebook(request.getFacebook());
        hotelContact.setInstagram(request.getInstagram());
        hotelContact.setPhoneNumber(request.getPhoneNumber());
        hotelContact.setPhoneNumberCode(request.getPhoneNumberCode());

        return hotelContactRepo.save(hotelContact);
    }
}
