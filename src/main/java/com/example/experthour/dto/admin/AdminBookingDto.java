package com.example.experthour.dto.admin;

import java.time.LocalDateTime;

public record AdminBookingDto(
        Long id,
        String userEmail,
        String expertName,
        LocalDateTime start,
        LocalDateTime end,
        LocalDateTime createdAt,
        String status
) {}



