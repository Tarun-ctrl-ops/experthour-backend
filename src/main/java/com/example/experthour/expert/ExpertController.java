package com.example.experthour.expert;

import com.example.experthour.dto.expert.AvailabilityDto;
import com.example.experthour.dto.expert.ExpertResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/experts")
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertService service;

    @GetMapping
    public List<ExpertResponseDto> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ExpertResponseDto create(@RequestBody Expert expert) {
        return service.create(expert);
    }

    @PreAuthorize("hasRole('EXPERT')")
    @PostMapping("/{id}/availability")
    public void saveAvailability(
            @PathVariable Long id,
            @RequestBody AvailabilityDto dto
    ) {
        service.saveAvailability(id, dto);
    }

    @GetMapping("/{id}/availability")
    public Map<String, Boolean> getAvailability(@PathVariable Long id) {
        return service.getAvailability(id);
    }
}
