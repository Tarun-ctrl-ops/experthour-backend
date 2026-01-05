package com.example.experthour.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class AvailabilityResponse {

    private UUID id;
    private UUID expertId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

