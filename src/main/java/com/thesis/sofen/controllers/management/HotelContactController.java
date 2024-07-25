package com.thesis.sofen.controllers.management;

import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.HotelContactRequest;
import com.thesis.sofen.services.HotelContactServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/management/hotels/{hotelId}/contacts")
@RequiredArgsConstructor
public class HotelContactController {
    private final HotelContactServices hotelContactServices;

    @PutMapping("")
    public ResponseEntity<?> update(@PathVariable("hotelId") UUID hotelId,
                                    @RequestBody @Valid HotelContactRequest request)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelContactServices.update(hotelId, request), HttpStatus.OK);
    }


}
