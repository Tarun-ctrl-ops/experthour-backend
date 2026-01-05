package com.example.experthour.repository;

import com.example.experthour.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {

    List<Availability> findByExpertId(UUID expertId);

    boolean existsByExpertIdAndStartTimeLessThanAndEndTimeGreaterThan(
            UUID expertId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );
}
