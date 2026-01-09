package com.example.experthour.admin;

import com.example.experthour.booking.Booking;
import com.example.experthour.booking.BookingRepository;
import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertRepository;
import com.example.experthour.user.User;
import com.example.experthour.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepo;
    private final ExpertRepository expertRepo;
    private final BookingRepository bookingRepo;

    public AdminService(UserRepository userRepo, ExpertRepository expertRepo, BookingRepository bookingRepo) {
        this.userRepo = userRepo;
        this.expertRepo = expertRepo;
        this.bookingRepo = bookingRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<Expert> getAllExperts() {
        return expertRepo.findAll();
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public Expert approveExpert(Long expertId) {
        Expert expert = expertRepo.findById(expertId).orElseThrow();
        return expertRepo.save(expert);
    }
}

