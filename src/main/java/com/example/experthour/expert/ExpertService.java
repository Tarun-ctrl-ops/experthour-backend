package com.example.experthour.expert;

import com.example.experthour.dto.expert.ExpertResponseDto;
import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertService {

    private final ExpertRepository repo;

    public Expert create(Expert expert) {
        expert.setStatus(ExpertStatus.APPROVED);
        return repo.save(expert);
    }

    public List<Expert> getAllApproved() {
        return repo.findByStatus(ExpertStatus.APPROVED);
    }

    public Expert updateAvailability(Long id, String from, String to) {
        Expert expert = repo.findById(id).orElseThrow();
        expert.setAvailableFrom(from);
        expert.setAvailableTo(to);
        return repo.save(expert);
    }

    public ExpertResponseDto toDto(Expert e) {
        return new ExpertResponseDto(
                e.getId(),
                e.getName(),
                e.getTitle(),
                e.getBio(),
                e.getSkills(),
                e.getPricePerHour(),
                e.getAvailableFrom(),
                e.getAvailableTo()
        );
    }
}

