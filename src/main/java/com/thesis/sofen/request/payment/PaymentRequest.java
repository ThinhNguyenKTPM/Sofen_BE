package com.thesis.sofen.request.payment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class PaymentRequest {
    private UUID bookingId;
    private Long amount;
    private int expiredTimeMinutes;
    private String title;
}
