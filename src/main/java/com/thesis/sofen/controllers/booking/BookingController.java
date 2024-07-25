package com.thesis.sofen.controllers.booking;

import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.request.booking.BookingPaymentRequest;
import com.thesis.sofen.request.booking.BookingRequest;
import com.thesis.sofen.response.PaymentUrlResponse;
import com.thesis.sofen.services.LanguageServices;
import com.thesis.sofen.services.booking.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/public/booking")
@Slf4j
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final LanguageServices languageService;

    @PostMapping("/create")
    public ResponseEntity<PaymentUrlResponse> createBooking(@RequestBody BookingRequest request,
                                                            HttpServletRequest servletRequest)
            throws ResourceNotFoundException, UnsupportedEncodingException {
        String lang = languageService.getCurrentLanguage(servletRequest);
        return ResponseEntity.ok(bookingService.createBooking(request, lang));
    }

    @GetMapping("/vnpay_return")
    public ResponseEntity<?> paymentReturn(
            @ModelAttribute BookingPaymentRequest req
    ) throws ResourceNotFoundException {
        var result = bookingService.paymentReturn(req);
        return ResponseEntity.ok(result);
    }
}
