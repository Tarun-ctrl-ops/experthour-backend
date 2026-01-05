package com.example.experthour.service;

import com.example.experthour.dto.AvailabilityResponse;
import com.example.experthour.dto.CreateAvailabilityRequest;
import com.example.experthour.exception.ValidationException;
import com.example.experthour.model.Availability;
import com.example.experthour.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public AvailabilityResponse createAvailability(CreateAvailabilityRequest request) {

        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new ValidationException("End time must be after start time");
        }

        boolean overlaps = availabilityRepository
                .existsByExpertIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        request.getExpertId(),
                        request.getEndTime(),
                        request.getStartTime()
                );

        if (overlaps) {
            throw new ValidationException("Availability slot overlaps with existing slot");
        }

        Availability availability = Availability.builder()
                .expertId(request.getExpertId())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        return toResponse(availabilityRepository.save(availability));
    }

    public List<AvailabilityResponse> getAvailabilityByExpert(UUID expertId) {
        return availabilityRepository.findByExpertId(expertId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AvailabilityResponse toResponse(Availability availability) {
        return AvailabilityResponse.builder()
                .id(availability.getId())
                .expertId(availability.getExpertId())
                .startTime(availability.getStartTime())
                .endTime(availability.getEndTime())
                .build();
    }
}
