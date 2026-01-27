package com.example.experthour.admin;

import com.example.experthour.booking.Booking;
import com.example.experthour.booking.BookingRepository;
import com.example.experthour.dto.admin.AdminBookingDto;
import com.example.experthour.dto.admin.AdminExpertDto;
import com.example.experthour.dto.admin.AdminUserDto;
import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertRepository;
import com.example.experthour.expert.ExpertStatus;
import com.example.experthour.mapper.AdminMapper;
import com.example.experthour.user.User;
import com.example.experthour.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ExpertRepository expertRepository;
    private final BookingRepository bookingRepository;
    private final AdminMapper adminMapper;

    public List<AdminUserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(adminMapper::toUserDto)
                .toList();
    }

    public List<AdminExpertDto> getAllExperts() {
        return expertRepository.findAll()
                .stream()
                .map(adminMapper::toExpertDto)
                .toList();
    }

    public List<AdminBookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(adminMapper::toBookingDto)
                .toList();
    }
}


