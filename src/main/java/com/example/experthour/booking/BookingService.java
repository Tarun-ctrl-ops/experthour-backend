package com.example.experthour.booking;

import com.example.experthour.dto.booking.BookingResponseDto;
import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertRepository;
import com.example.experthour.user.User;
import com.example.experthour.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;
    private final UserRepository userRepo;
    private final ExpertRepository expertRepo;

    public BookingResponseDto createBooking(
            String email,
            Long expertId,
            LocalDateTime start,
            LocalDateTime end
    ) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Expert expert = expertRepo.findById(expertId).orElseThrow();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setExpert(expert);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setStatus("CONFIRMED");

        Booking saved = bookingRepo.save(booking);
        return toDto(saved);
    }

    public List<BookingResponseDto> getUserBookings(String email) {
        return bookingRepo.findByUserEmail(email)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private BookingResponseDto toDto(Booking b) {
        return new BookingResponseDto(
                b.getId(),
                b.getExpert().getId(),
                b.getExpert().getName(),
                b.getStartTime().toString(),
                b.getEndTime().toString(),
                b.getStatus()
        );
    }
}
