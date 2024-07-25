package com.thesis.sofen.controllers.management;

import com.thesis.sofen.exception.MethodArgumentNotValidException;
import com.thesis.sofen.request.booking.BookingProfitRequest;
import com.thesis.sofen.request.filter.BookingHistoryRequest;
import com.thesis.sofen.response.BookingHistoryResponse;
import com.thesis.sofen.services.booking.BookingService;
import com.thesis.sofen.services.management.BookingManagementService;
import com.thesis.sofen.util.ArgumentNotValid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/management/booking")
@RequiredArgsConstructor
public class BookingManagementController {
    private final BookingManagementService bookingManagementService;
    @GetMapping("/profit")
    public ResponseEntity<?> getProfit(@ModelAttribute BookingProfitRequest request) {
        var response = bookingManagementService.getBookingProfit(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/profit/time")
    public ResponseEntity<?> getProfitByTime(@ModelAttribute BookingProfitRequest request) {
        var response = bookingManagementService.getBookingProfitByTime(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/created")
    public ResponseEntity<?> getCreated(@ModelAttribute BookingProfitRequest request, BindingResult bind)
            throws MethodArgumentNotValidException {
        ArgumentNotValid.handleInvalidArgument(bind);
        var response = bookingManagementService.getBookingCreated(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<BookingHistoryResponse>> getBooking(@ModelAttribute BookingHistoryRequest request, BindingResult bind)
            throws MethodArgumentNotValidException {
        ArgumentNotValid.handleInvalidArgument(bind);
        Pageable pageable = request.getPageAndSort();
        var response = bookingManagementService.getBooking(request, pageable);
        return ResponseEntity.ok(response);
    }

}
