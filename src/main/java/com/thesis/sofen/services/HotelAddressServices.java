package com.thesis.sofen.services;


import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.entities.HotelAddress;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.repositories.HotelAddressRepository;
import com.thesis.sofen.repositories.HotelRepository;
import com.thesis.sofen.request.HotelAddressDetailRequest;
import com.thesis.sofen.request.HotelAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelAddressServices {
    private final HotelAddressRepository hotelAddressRepo;
    private final HotelRepository hotelRepo;

    public Object update(UUID hotelId, HotelAddressDetailRequest request)
            throws ResourceNotFoundException {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("hotel_not_found", "Không tìm thấy khách sạn với id: " + hotelId)
        );
        hotel.setAddressDetail(request.getDetail());
        HotelAddress hotelAddress = hotel.getHotelAddress();
        if(hotelAddress == null){
            hotelAddress = new HotelAddress();
        }
        HotelAddressRequest hotelAddressRequest = request.getAddress();
        hotelAddress.setNation(hotelAddressRequest.getNation());
        hotelAddress.setProvince(hotelAddressRequest.getProvince());
        hotelAddress.setDistrict(hotelAddressRequest.getDistrict());
        hotelAddress.setWard(hotelAddressRequest.getWard());

        hotel.setHotelAddress(hotelAddress);
        hotelRepo.save(hotel);
        return null;
    }
}
