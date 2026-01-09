package com.example.experthour.availability;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService service;

    @PostMapping("/{expertId}")
    public Availability create(@PathVariable Long expertId,
                               @RequestBody Availability a) {
        return service.create(expertId, a);
    }

    @GetMapping("/{expertId}")
    public List<Availability> get(@PathVariable Long expertId) {
        return service.getForExpert(expertId);
    }
}

