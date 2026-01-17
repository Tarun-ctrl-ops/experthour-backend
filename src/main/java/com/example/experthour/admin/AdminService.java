package com.example.experthour.admin;

import com.example.experthour.booking.Booking;
import com.example.experthour.booking.BookingRepository;
import com.example.experthour.dto.admin.AdminBookingDto;
import com.example.experthour.dto.admin.AdminExpertDto;
import com.example.experthour.dto.admin.AdminUserDto;
import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertRepository;
import com.example.experthour.mapper.AdminMapper;
import com.example.experthour.user.User;
import com.example.experthour.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
public class AdminService {

    private final UserRepository userRepo;
    private final ExpertRepository expertRepo;
    private final BookingRepository bookingRepo;

    public AdminService(UserRepository userRepo,
                        ExpertRepository expertRepo,
                        BookingRepository bookingRepo) {
        this.userRepo = userRepo;
        this.expertRepo = expertRepo;
        this.bookingRepo = bookingRepo;
    }

    // ✅ USERS
    public List<AdminUserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(AdminMapper::toUserDto)
                .toList();
    }

    // ✅ EXPERTS
    public List<AdminExpertDto> getAllExperts() {
        return expertRepo.findAll()
                .stream()
                .map(AdminMapper::toExpertDto)
                .toList();
    }

    // ✅ BOOKINGS
    public List<AdminBookingDto> getAllBookings() {
        return bookingRepo.findAll()
                .stream()
                .map(AdminMapper::toBookingDto)
                .toList();
    }

    // ✅ APPROVE EXPERT
    public AdminExpertDto approveExpert(Long expertId) {
        Expert expert = expertRepo.findById(expertId)
                .orElseThrow(() -> new RuntimeException("Expert not found"));

        expert.setApproved(true);
        expertRepo.save(expert);

        return AdminMapper.toExpertDto(expert);
    }


}
