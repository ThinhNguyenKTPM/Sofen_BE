package com.thesis.sofen.controllers.external;

import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.hotel.SearchHotelRequest;
import com.thesis.sofen.response.External.RoomDetailResponse;
import com.thesis.sofen.services.LanguageServices;
import com.thesis.sofen.services.external.RoomExternalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/public/rooms")
@Slf4j
@RequiredArgsConstructor
public class ExternalRoomController {
    private final LanguageServices languageServices;
    private final RoomExternalService roomExternalService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<RoomDetailResponse>> getRoomDetail(
            @PathVariable UUID hotelId,
            HttpServletRequest servletRequest,
            @ModelAttribute SearchHotelRequest query
    )
            throws ResourceNotFoundException {
        String lang = languageServices.getCurrentLanguage(servletRequest);
        var response = roomExternalService.getRoomDetail(hotelId, lang, query);
        return ResponseEntity.ok(response);
    }
}
