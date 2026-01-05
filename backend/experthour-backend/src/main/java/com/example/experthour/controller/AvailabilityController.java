package com.example.experthour.controller;

import com.example.experthour.dto.AvailabilityResponse;
import com.example.experthour.dto.CreateAvailabilityRequest;
import com.example.experthour.service.AvailabilityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    public AvailabilityResponse createAvailability(
            @Valid @RequestBody CreateAvailabilityRequest request) {
        return availabilityService.createAvailability(request);
    }

    @GetMapping("/expert/{expertId}")
    public List<AvailabilityResponse> getAvailabilityByExpert(
            @PathVariable UUID expertId) {
        return availabilityService.getAvailabilityByExpert(expertId);
    }
}
