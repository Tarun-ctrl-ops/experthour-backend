package com.example.experthour.booking;

import com.example.experthour.dto.booking.BookingResponseDto;
import com.example.experthour.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class BookingController {

    private final BookingService service;
    private final BookingMapper mapper;

    @PostMapping
    public BookingResponseDto create(
            @RequestBody Map<String, String> req,
            Principal principal
    ) {
        Booking booking = service.createBooking(
                principal.getName(),
                Long.parseLong(req.get("expertId")),
                LocalDateTime.parse(req.get("start")),
                LocalDateTime.parse(req.get("end"))
        );
        return mapper.toDto(booking);
    }

    @GetMapping("/my")
    public List<BookingResponseDto> myBookings(Principal principal) {
        return service.getUserBookings(principal.getName())
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/expert/{id}")
    public List<BookingResponseDto> expertBookings(@PathVariable Long id) {
        return service.getExpertBookings(id)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
