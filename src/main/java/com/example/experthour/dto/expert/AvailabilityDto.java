package com.example.experthour.dto.expert;

import java.time.LocalTime;

public record AvailabilityDto(
        LocalTime from,
        LocalTime to
) {}
