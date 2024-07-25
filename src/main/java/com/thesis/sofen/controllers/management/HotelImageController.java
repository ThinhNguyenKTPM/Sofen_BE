package com.thesis.sofen.controllers.management;

import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.dto.ImageDTO;
import com.thesis.sofen.services.HotelImageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/management/hotels/{hotel_id}/images")
@RequiredArgsConstructor
public class HotelImageController {
    private final HotelImageServices hotelImageServices;
    @PostMapping(value = "")
    public ResponseEntity<?> createImage(
            @PathVariable("hotel_id") UUID hotelId,
            @RequestBody ImageDTO request) throws ResourceNotFoundException {
        return hotelImageServices.createImage(hotelId, request);
    }
}
