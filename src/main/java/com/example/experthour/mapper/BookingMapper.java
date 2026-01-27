package com.example.experthour.mapper;

import com.example.experthour.booking.Booking;
import com.example.experthour.dto.booking.BookingResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponseDto toDto(Booking booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getExpert().getId(),
                booking.getExpert().getName(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getPrice(),
                booking.getStatus().name()
        );
    }
}

