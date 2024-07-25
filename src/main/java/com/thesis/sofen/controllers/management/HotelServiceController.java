package com.thesis.sofen.controllers.management;

import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.hotelService.HotelServiceRequest;
import com.thesis.sofen.services.HotelServiceServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/management/services")
@Slf4j
@RequiredArgsConstructor
public class HotelServiceController {
    private final HotelServiceServices hotelServices;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody HotelServiceRequest request)
            throws DuplicateFieldException, ResourceNotFoundException {

        return  new ResponseEntity<>(hotelServices.createService(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody HotelServiceRequest request)
            throws DuplicateFieldException, ResourceNotFoundException {

        return new ResponseEntity<>(hotelServices.updateService(request, id), HttpStatus.OK);
    }
    @PutMapping("{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable(name = "id") Long id, @RequestParam(name = "status") EUserStatus status)
            throws DuplicateFieldException, ResourceNotFoundException {

        return new ResponseEntity<>(hotelServices.updateServiceStatus(id, status), HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getAllByHotel(@PathVariable(name = "hotelId") UUID hotelId) {
        return new ResponseEntity<>(hotelServices.getAllHotelService(hotelId), HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(hotelServices.getAllHotelService(null), HttpStatus.OK);
    }



}
