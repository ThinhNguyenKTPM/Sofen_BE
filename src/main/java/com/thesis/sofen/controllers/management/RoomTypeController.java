package com.thesis.sofen.controllers.management;

import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.roomType.RoomTypeRequest;
import com.thesis.sofen.services.RoomTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management/room-type")
@RequiredArgsConstructor
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(roomTypeService.getAllRoomType(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@Valid @RequestBody RoomTypeRequest request)
            throws DuplicateFieldException, ResourceNotFoundException {

        return new ResponseEntity<>(roomTypeService.create(request), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RoomTypeRequest request, @PathVariable Long id)
            throws DuplicateFieldException, ResourceNotFoundException {

        return new ResponseEntity<>(roomTypeService.update(request, id), HttpStatus.OK);
    }


}
