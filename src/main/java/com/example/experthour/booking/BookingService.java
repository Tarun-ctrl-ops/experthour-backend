package com.example.experthour.booking;

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

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ExpertRepository expertRepository;

    public Booking createBooking(
            String userEmail,
            Long expertId,
            LocalDateTime start,
            LocalDateTime end
    ) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new RuntimeException("Expert not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setExpert(expert);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setPrice(expert.getPricePerHour());
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(String email) {
        return bookingRepository.findByUserEmail(email);
    }

    public List<Booking> getExpertBookings(Long expertId) {
        return bookingRepository.findByExpertId(expertId);
    }
}
