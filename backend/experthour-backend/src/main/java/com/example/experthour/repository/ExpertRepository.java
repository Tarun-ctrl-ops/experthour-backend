package com.example.experthour.repository;

import com.example.experthour.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExpertRepository extends JpaRepository<Expert, UUID> {

    Optional<Expert> findByEmail(String email);

    boolean existsByEmail(String email);
}
