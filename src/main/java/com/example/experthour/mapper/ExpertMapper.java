package com.example.experthour.mapper;

import com.example.experthour.dto.expert.ExpertResponseDto;
import com.example.experthour.expert.Expert;
import org.springframework.stereotype.Component;

@Component
public class ExpertMapper {

    public ExpertResponseDto toDto(Expert expert) {
        return new ExpertResponseDto(
                expert.getId(),
                expert.getName(),
                expert.getTitle(),
                expert.getBio(),          // ✅ FIXED (was getDescription)
                expert.getSkills(),
                expert.getPricePerHour(),
                expert.getStatus().name(),
                expert.getAvailableFrom(),
                expert.getAvailableTo()
        );
    }
}

