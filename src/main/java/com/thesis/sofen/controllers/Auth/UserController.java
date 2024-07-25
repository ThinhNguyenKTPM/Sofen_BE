package com.thesis.sofen.controllers.Auth;


import com.thesis.sofen.entities.User;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.filter.BookingHistoryRequest;
import com.thesis.sofen.response.BookingHistoryResponse;
import com.thesis.sofen.services.auth.AccountService;
import com.thesis.sofen.services.auth.UserDetailsImpl;
import com.thesis.sofen.services.booking.BookingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserController {
    private final AccountService accountService;
    private final BookingService bookingService;

    @GetMapping(value = "/account")
    public ResponseEntity<User> find() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User result = accountService.find(userDetails.getEmail());
        return ResponseEntity.ok(result);
    }


    @PutMapping(value = "/account")
    public ResponseEntity<User> update(@RequestBody User command) throws ResourceNotFoundException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User results = accountService.update(userDetails.getEmail(), command);
        return ResponseEntity.ok(results);
    }

    @GetMapping(value = "/booking-history")
    public ResponseEntity<?> bookingHistory(
            @ModelAttribute BookingHistoryRequest request
    ) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pageable pageable = request.getPageAndSort();
        String status = request.getStatus() == null ? "" : request.getStatus();
        Page<BookingHistoryResponse> result = bookingService.getBookingHistoryByUser(userDetails.getId(),pageable, request.getStatus());
        return ResponseEntity.ok(result);
    }

}
