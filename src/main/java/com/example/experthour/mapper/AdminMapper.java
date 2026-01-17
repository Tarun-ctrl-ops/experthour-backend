package com.example.experthour.mapper;

import com.example.experthour.booking.Booking;
import com.example.experthour.dto.admin.AdminBookingDto;
import com.example.experthour.dto.admin.AdminExpertDto;
import com.example.experthour.dto.admin.AdminUserDto;
import com.example.experthour.expert.Expert;
import com.example.experthour.user.User;

public class AdminMapper {

    // USERS
    public static AdminUserDto toUserDto(User user) {
        return new AdminUserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // EXPERTS
    public static AdminExpertDto toExpertDto(Expert expert) {
        return new AdminExpertDto(
                expert.getId(),
                expert.getName(),
                expert.isApproved()
        );
    }

    // BOOKINGS
    public static AdminBookingDto toBookingDto(Booking booking) {
        return new AdminBookingDto(
                booking.getId(),
                booking.getUser() != null ? booking.getUser().getId() : null,
                booking.getExpert() != null ? booking.getExpert().getId() : null,
                booking.getBookedAt()
        );
    }


}

