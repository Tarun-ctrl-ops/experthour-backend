package com.example.experthour.dto.admin;

import java.time.LocalDateTime;

public class AdminBookingDto {

    private Long id;
    private Long userId;
    private Long expertId;
    private LocalDateTime bookedAt;

    public AdminBookingDto(Long id, Long userId, Long expertId, LocalDateTime bookedAt) {
        this.id = id;
        this.userId = userId;
        this.expertId = expertId;
        this.bookedAt = bookedAt;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getExpertId() { return expertId; }
    public LocalDateTime getBookedAt() { return bookedAt; }
}


