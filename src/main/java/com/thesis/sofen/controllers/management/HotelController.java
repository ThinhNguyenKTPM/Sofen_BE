package com.thesis.sofen.controllers.management;

import com.thesis.sofen.entities.HotelImage;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.filter.FilterHotelRequest;
import com.thesis.sofen.request.hotel.CreateHotelRequest;
import com.thesis.sofen.services.HotelServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/management/hotels")
@Slf4j
@RequiredArgsConstructor
public class HotelController {
    private final HotelServices hotelServices;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllHotelName(){
        return new ResponseEntity<>(hotelServices.getAllHotelName(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable UUID id) throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelServices.getHotelById(id), HttpStatus.OK);
    }
    @GetMapping(value = "/filter")
    public ResponseEntity<?> getAllHotel(@ModelAttribute FilterHotelRequest request){
        Pageable pageable = request.getPageAndSort();
        return new ResponseEntity<>(hotelServices.filterHotel(pageable, request), HttpStatus.OK);
    }
    public ResponseEntity<String> find() {
        return ResponseEntity.ok("Get Hotel");
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody @Valid CreateHotelRequest request) throws DuplicateFieldException {
        return new ResponseEntity<>(hotelServices.createHotel(request), HttpStatus.CREATED);
    }

    /**
     * Add a policy into hotel
     */
    @PutMapping(value = "/{hotelId}/policies/{policyId}")
    public ResponseEntity<?> addingPolicy(@PathVariable UUID hotelId, @PathVariable Long policyId)
            throws ResourceNotFoundException, DuplicateFieldException {
        return new ResponseEntity<>(hotelServices.addPolicy(hotelId, policyId), HttpStatus.OK);
    }
    /**
     * Remove a policy from hotel
     */
    @DeleteMapping(value = "/{hotelId}/policies/{policyId}")
    public ResponseEntity<?> removePolicy(@PathVariable UUID hotelId, @PathVariable Long policyId)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelServices.removePolicy(hotelId, policyId), HttpStatus.OK);
    }

    /**
     * Add a service into hotel
     */
    @PutMapping(value = "/{hotelId}/services/{serviceId}")
    public ResponseEntity<?> addingService(@PathVariable UUID hotelId, @PathVariable Long serviceId)
            throws ResourceNotFoundException, DuplicateFieldException {
        return new ResponseEntity<>(hotelServices.addService(hotelId, serviceId), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{hotelId}/services/{serviceId}")
    public ResponseEntity<?> removeService(@PathVariable UUID hotelId, @PathVariable Long serviceId)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelServices.removeService(hotelId, serviceId), HttpStatus.OK);
    }

    @PostMapping(value = "/{hotelId}/image")
    public ResponseEntity<?> addImage(@PathVariable UUID hotelId, @RequestBody HotelImage image)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelServices.addImage(hotelId, image), HttpStatus.OK);
    }
    @PutMapping(value = "/{hotelId}/image")
    public ResponseEntity<?> updateImage(@PathVariable UUID hotelId, @RequestBody HotelImage image)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelServices.updateImage(hotelId, image), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{hotelId}/image/{imageId}")
    public ResponseEntity<?> removeImage(@PathVariable UUID hotelId, @PathVariable Long imageId)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(hotelServices.removeImage(hotelId, imageId), HttpStatus.OK);
    }





}
