package com.example.experthour.dto.expert;

public record ExpertDto(
        Long id,
        String name,
        String title,
        double pricePerHour
) {}

