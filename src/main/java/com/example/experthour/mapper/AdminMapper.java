package com.example.experthour.mapper;

import com.example.experthour.booking.Booking;
import com.example.experthour.dto.admin.AdminBookingDto;
import com.example.experthour.dto.admin.AdminExpertDto;
import com.example.experthour.dto.admin.AdminUserDto;
import com.example.experthour.expert.Expert;
import com.example.experthour.user.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    // ---------- USERS ----------
    public AdminUserDto toUserDto(User user) {
        return new AdminUserDto(
                user.getId(),
                user.getEmail(),
                user.getRole().name(),   // enum → OK
                user.isEnabled()
        );
    }

    // ---------- EXPERTS ----------
    public AdminExpertDto toExpertDto(Expert expert) {
        return new AdminExpertDto(
                expert.getId(),
                expert.getName(),        // String → DIRECT
                expert.getTitle(),
                expert.getBio(),
                expert.getSkills(),
                expert.getPricePerHour(),
                expert.getStatus().name() // enum → OK
        );
    }

    // ---------- BOOKINGS ----------
    public AdminBookingDto toBookingDto(Booking booking) {
        return new AdminBookingDto(
                booking.getId(),
                booking.getUser().getEmail(),
                booking.getExpert().getName(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getCreatedAt(),
                booking.getStatus().name()
        );
    }
}


