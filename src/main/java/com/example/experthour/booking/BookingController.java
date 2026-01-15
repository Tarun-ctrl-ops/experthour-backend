package com.example.experthour.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    public Booking create(@RequestBody Map<String, String> req, Principal principal) {

        Long expertId = Long.parseLong(req.get("expertId"));
        LocalDateTime start = LocalDateTime.parse(req.get("start"));
        LocalDateTime end = LocalDateTime.parse(req.get("end"));

        return service.createBooking(principal.getName(), expertId, start, end);
    }

    @GetMapping("/my")
    public List<Booking> myBookings(Principal principal) {
        return service.getUserBookings(principal.getName());
    }

    @GetMapping("/expert/{id}")
    public List<Booking> expertBookings(@PathVariable Long id) {
        return service.getExpertBookings(id);
    }
}

