package com.example.experthour.dto.stripe;

public record CreateCheckoutSessionRequest(
        Long bookingId
) {}
