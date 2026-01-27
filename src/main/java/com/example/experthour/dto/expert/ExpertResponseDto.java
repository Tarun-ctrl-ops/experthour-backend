package com.example.experthour.dto.expert;

public record ExpertResponseDto(
        Long id,
        String name,
        String title,
        String bio,
        String skills,
        double pricePerHour,
        String availableFrom,
        String availableTo
) {}

