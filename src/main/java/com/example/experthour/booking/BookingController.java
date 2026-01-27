package com.example.experthour.booking;
import com.example.experthour.dto.booking.BookingResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    public BookingResponseDto create(
            @RequestBody Map<String, String> req,
            Principal principal
    ) {
        return service.createBooking(
                principal.getName(),
                Long.parseLong(req.get("expertId")),
                LocalDateTime.parse(req.get("start")),
                LocalDateTime.parse(req.get("end"))
        );
    }

    @GetMapping("/my")
    public List<BookingResponseDto> myBookings(Principal principal) {
        return service.getUserBookings(principal.getName());
    }
}


