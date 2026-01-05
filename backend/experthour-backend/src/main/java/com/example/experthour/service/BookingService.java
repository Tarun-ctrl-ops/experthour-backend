package com.example.experthour.service;

import com.example.experthour.dto.BookingResponse;
import com.example.experthour.dto.CreateBookingRequest;
import com.example.experthour.exception.ResourceNotFoundException;
import com.example.experthour.exception.ValidationException;
import com.example.experthour.model.Booking;
import com.example.experthour.model.BookingStatus;
import com.example.experthour.repository.AvailabilityRepository;
import com.example.experthour.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;


    private final AvailabilityRepository availabilityRepository;

    public BookingService(
            BookingRepository bookingRepository,
            AvailabilityRepository availabilityRepository) {

        this.bookingRepository = bookingRepository;
        this.availabilityRepository = availabilityRepository;
    }

    public BookingResponse createBooking(CreateBookingRequest request) {

        // 1️⃣ Basic time validation
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new ValidationException("End time must be after start time");
        }

        long hours = Duration.between(
                request.getStartTime(),
                request.getEndTime()
        ).toHours();

        if (hours <= 0) {
            throw new ValidationException("Booking duration must be at least 1 hour");
        }

        // 2️⃣ AVAILABILITY CHECK (THIS IS THE NEW PART)
        boolean available = availabilityRepository
                .existsByExpertIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        request.getExpertId(),
                        request.getEndTime(),
                        request.getStartTime()
                );

        if (!available) {
            throw new ValidationException("Expert is not available for this time slot");
        }

        // 3️⃣ Pricing logic
        double price = hours * 1000; // simple pricing for now

        // 4️⃣ Save booking
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .expertId(request.getExpertId())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .price(price)
                .status(BookingStatus.CREATED)
                .build();

        return toResponse(bookingRepository.save(booking));
    }

    public List<BookingResponse> getBookingsByUser(UUID userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<BookingResponse> getBookingsByExpert(UUID expertId) {
        return bookingRepository.findByExpertId(expertId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void cancelBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    private BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .expertId(booking.getExpertId())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .price(booking.getPrice())
                .status(booking.getStatus())
                .build();
    }
}
