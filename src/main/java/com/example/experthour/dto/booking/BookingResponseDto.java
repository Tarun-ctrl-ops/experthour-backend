package com.example.experthour.dto.booking;

public record BookingResponseDto(
        Long id,
        Long expertId,
        String expertName,
        String startTime,
        String endTime,
        String status
) {}
