package com.example.experthour.expert;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    List<Expert> findByStatus(ExpertStatus status);
    Optional<Expert> findByUserEmail(String email);

}

