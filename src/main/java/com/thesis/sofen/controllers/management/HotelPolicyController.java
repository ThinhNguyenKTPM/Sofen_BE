package com.thesis.sofen.controllers.management;

import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.filter.PageAndFilter;
import com.thesis.sofen.request.hotelPolicy.HotelPoliciesRequest;
import com.thesis.sofen.services.HotelPolicyServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/management/policies")
@Slf4j
@RequiredArgsConstructor
public class HotelPolicyController {
    private final HotelPolicyServices hotelPolicyServices;

    @GetMapping("")
    public ResponseEntity<?> filter(HttpServletRequest servletRequest, PageAndFilter pageAndFilter) {
        String lang = servletRequest.getHeader("Accept-Language");
        return hotelPolicyServices.findPolicies();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody HotelPoliciesRequest request)
            throws DuplicateFieldException, ResourceNotFoundException {
        return new ResponseEntity<>(
                hotelPolicyServices.createPolicies(request),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<?> update(@PathVariable Long policyId, @RequestBody HotelPoliciesRequest request)
            throws ResourceNotFoundException, DuplicateFieldException {
        var response = hotelPolicyServices.updatePolicies(policyId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{policyId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long policyId, @RequestParam EUserStatus status)
            throws ResourceNotFoundException, DuplicateFieldException {
        var response = hotelPolicyServices.updateStatus(policyId, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getAllByHotel(@PathVariable(name = "hotelId") UUID hotelId) {
        return new ResponseEntity<>(hotelPolicyServices.getAllHotelPolicy(hotelId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(hotelPolicyServices.getAllHotelPolicy(null), HttpStatus.OK);
    }

}
