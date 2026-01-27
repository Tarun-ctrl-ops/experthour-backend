package com.example.experthour.dto.expert;

import com.example.experthour.expert.ExpertStatus;

public record ExpertResponseDto(
        Long id,
        String name,
        String title,
        String bio,
        String skills,
        double pricePerHour,
        String status,
        String availableFrom,
        String availableTo
) {}

