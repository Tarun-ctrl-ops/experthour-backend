package com.example.experthour.expert;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/experts")
@CrossOrigin
public class ExpertController {

    private final ExpertService service;

    public ExpertController(ExpertService service) {
        this.service = service;
    }

    @GetMapping
    public List<Expert> getAll() {
        return service.getAllApproved();
    }

    @PostMapping
    public Expert create(@RequestBody Expert expert) {
        return service.create(expert);
    }

    @PutMapping("/{id}/availability")
    public Expert setAvailability(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        return service.updateAvailability(
                id,
                body.get("from"),
                body.get("to")
        );
    }
}
