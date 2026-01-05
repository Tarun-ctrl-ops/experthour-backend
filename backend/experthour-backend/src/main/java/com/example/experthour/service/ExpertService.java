package com.example.experthour.service;

import com.example.experthour.dto.CreateExpertRequest;
import com.example.experthour.dto.ExpertResponse;
import com.example.experthour.dto.UpdateExpertRequest;
import com.example.experthour.exception.ResourceNotFoundException;
import com.example.experthour.exception.ValidationException;
import com.example.experthour.model.Expert;
import com.example.experthour.repository.ExpertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpertService {

    private final ExpertRepository expertRepository;

    public ExpertService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    public List<ExpertResponse> getAllExperts() {
        return expertRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ExpertResponse getExpertById(UUID id) {
        Expert expert = expertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expert not found"));
        return toResponse(expert);
    }

    public ExpertResponse createExpert(CreateExpertRequest request) {

        if (expertRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException("Email already exists");
        }

        Expert expert = Expert.builder()
                .name(request.getName())
                .email(request.getEmail())
                .domain(request.getDomain())
                .experience(request.getExperience())
                .hourlyRate(request.getHourlyRate())
                .rating(0.0)
                .build();

        return toResponse(expertRepository.save(expert));
    }

    public ExpertResponse updateExpert(UUID id, UpdateExpertRequest request) {

        Expert expert = expertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expert not found"));

        expert.setName(request.getName());
        expert.setDomain(request.getDomain());
        expert.setExperience(request.getExperience());
        expert.setHourlyRate(request.getHourlyRate());

        return toResponse(expertRepository.save(expert));
    }

    public void deleteExpert(UUID id) {
        Expert expert = expertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expert not found"));

        expertRepository.delete(expert);
    }

    private ExpertResponse toResponse(Expert expert) {
        return ExpertResponse.builder()
                .id(expert.getId())
                .name(expert.getName())
                .email(expert.getEmail())
                .domain(expert.getDomain())
                .experience(expert.getExperience())
                .hourlyRate(expert.getHourlyRate())
                .rating(expert.getRating())
                .build();
    }
}
