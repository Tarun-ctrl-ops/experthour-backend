package com.example.experthour.dto.booking;

import java.time.LocalDateTime;

public record BookingResponseDto(
        Long id,
        Long expertId,
        String expertName,
        LocalDateTime start,
        LocalDateTime end,
        double price,
        String status
) {}

