package com.example.experthour.dto;

import com.example.experthour.model.BookingStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class BookingResponse {

    private UUID id;
    private UUID userId;
    private UUID expertId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double price;
    private BookingStatus status;
}
