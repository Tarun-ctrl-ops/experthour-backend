package com.example.experthour.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserEmail(String email);

    List<Booking> findByExpertId(Long expertId);
}

