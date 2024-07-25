package com.thesis.sofen.controllers.management;

import com.thesis.sofen.entities.Furniture.RoomFurniture;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.RoomFurnitureRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.thesis.sofen.services.RoomFurnitureService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management/room-furniture")
@RequiredArgsConstructor
public class RoomFurnitureController {
    private final RoomFurnitureService roomFurnitureService;

    @PostMapping(value = "")
    public ResponseEntity<?> create(@Valid @RequestBody RoomFurnitureRequest request)
            throws DuplicateFieldException, ResourceNotFoundException {
        RoomFurniture roomFurniture = roomFurnitureService.create(request);
        return new ResponseEntity<>(roomFurniture, HttpStatus.CREATED);
    }

    @PostMapping(value = "/list")
    public ResponseEntity<?> createList(@Valid @RequestBody List<RoomFurnitureRequest> request)
            throws DuplicateFieldException, ResourceNotFoundException {
        List<RoomFurniture> roomFurniture = roomFurnitureService.createList(request);
        return new ResponseEntity<>(roomFurniture, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{roomId}")
    public ResponseEntity<?> getAllByRoom(@PathVariable(name = "roomId") Long roomId) {
        return ResponseEntity.ok(roomFurnitureService.getAllRoomFurniture(roomId));
    }
    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roomFurnitureService.getAllRoomFurniture(null));
    }



}
