package com.example.experthour.dto.booking;

import com.example.experthour.dto.expert.ExpertDto;
import com.example.experthour.dto.user.UserDto;

import java.time.LocalDateTime;

public record BookingDto(
        Long id,
        UserDto user,
        ExpertDto expert,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String status
) {}
