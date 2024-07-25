package com.thesis.sofen.controllers.management;

import com.thesis.sofen.entities.Room;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.RoomRequest;
import com.thesis.sofen.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/management/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @PostMapping(value = "")
    public ResponseEntity<?> create(@Valid @RequestBody RoomRequest request)
            throws ResourceNotFoundException {
        var room = roomService.create(request);
        return null;
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Room room)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(roomService.update(id, room));
    }
    @GetMapping(value = "/{hotelId}")
    public ResponseEntity<?> getAllByHotel(@PathVariable(name = "hotelId") UUID hotelId) {

        return ResponseEntity.ok(roomService.getAllHotelRoom(hotelId));
    }
    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roomService.getAllHotelRoom(null));
    }
}
