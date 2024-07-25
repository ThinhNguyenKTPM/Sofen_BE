package com.thesis.sofen.controllers.external;


import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.hotel.HotelDestinationRequest;
import com.thesis.sofen.request.hotel.SearchHotelRequest;
import com.thesis.sofen.response.External.Hotel.HotelDetailResponse;
import com.thesis.sofen.response.External.RoomDetailResponse;
import com.thesis.sofen.services.LanguageServices;
import com.thesis.sofen.services.external.HotelExternalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/public/hotels")
@Slf4j
@RequiredArgsConstructor
public class ExternalHotelController {
    private final HotelExternalService hotelExternalService;
    private final LanguageServices languageService;
    @GetMapping("")
    public ResponseEntity<List<RoomDetailResponse>> find(@ModelAttribute SearchHotelRequest query, HttpServletRequest servletRequest)
            throws ResourceNotFoundException {
        String lang = languageService.getCurrentLanguage(servletRequest);
        System.out.println(query.toString());
        return ResponseEntity.ok(hotelExternalService.filter(query, lang));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDetailResponse> getHotelDetail(@PathVariable UUID hotelId, HttpServletRequest servletRequest)
            throws ResourceNotFoundException {
        String lang = languageService.getCurrentLanguage(servletRequest);
        var response = hotelExternalService.getHotelDetailByID(hotelId, lang);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/destinations")
    public ResponseEntity<?> getAllDestination(HttpServletRequest servletRequest) {
        String lang = languageService.getCurrentLanguage(servletRequest);
        return ResponseEntity.ok(hotelExternalService.getNationality(lang));
    }
    @GetMapping("/destinations/hotel")
    public ResponseEntity<?> getHotelByDestination(@ModelAttribute HotelDestinationRequest query,
                                                   HttpServletRequest servletRequest) {
        String lang = languageService.getCurrentLanguage(servletRequest);
        return ResponseEntity.ok(hotelExternalService.getHotelByDestination(query.getNation(),query.getProvince(), lang));
    }
    @GetMapping("/names")
    public ResponseEntity<?> geListHotelName(HttpServletRequest servletRequest) {
        String lang = languageService.getCurrentLanguage(servletRequest);
        return ResponseEntity.ok(hotelExternalService.getListHotelName(lang));
    }
}
