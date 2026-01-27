package com.example.experthour.expert;

import com.example.experthour.dto.expert.ExpertResponseDto;
import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertService;
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
        return service.getAllApproved()
                .stream()
                .map(service::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ExpertResponseDto create(@RequestBody Expert expert) {
        return service.toDto(service.create(expert));
    }

    @PreAuthorize("hasRole('EXPERT')")
    @PutMapping("/{id}/availability")
    public ExpertResponseDto setAvailability(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        return service.toDto(
                service.updateAvailability(id, body.get("from"), body.get("to"))
        );
    }
}
