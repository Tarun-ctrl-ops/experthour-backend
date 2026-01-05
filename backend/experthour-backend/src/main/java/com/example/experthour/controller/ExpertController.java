package com.example.experthour.controller;

import com.example.experthour.dto.CreateExpertRequest;
import com.example.experthour.dto.ExpertResponse;
import com.example.experthour.dto.UpdateExpertRequest;
import com.example.experthour.service.ExpertService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/experts")
public class ExpertController {

    private final ExpertService expertService;

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @GetMapping
    public List<ExpertResponse> getAllExperts() {
        return expertService.getAllExperts();
    }

    @GetMapping("/{id}")
    public ExpertResponse getExpertById(@PathVariable UUID id) {
        return expertService.getExpertById(id);
    }

    @PostMapping
    public ExpertResponse createExpert(@Valid @RequestBody CreateExpertRequest request) {
        return expertService.createExpert(request);
    }

    @PutMapping("/{id}")
    public ExpertResponse updateExpert(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateExpertRequest request) {
        return expertService.updateExpert(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteExpert(@PathVariable UUID id) {
        expertService.deleteExpert(id);
    }
}