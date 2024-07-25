package com.thesis.sofen.controllers.management;

import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.HotelAddressDetailRequest;
import com.thesis.sofen.request.HotelContactRequest;
import com.thesis.sofen.services.HotelAddressServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/management/hotels/{hotelId}/address")
@RequiredArgsConstructor
public class HotelAddressController {
    private final HotelAddressServices hotelAddressServices;

    @PutMapping("")
    public ResponseEntity<?> update(@PathVariable("hotelId") UUID hotelId,
                                    @RequestBody @Valid HotelAddressDetailRequest request)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelAddressServices.update(hotelId, request), HttpStatus.OK);
    }
}
