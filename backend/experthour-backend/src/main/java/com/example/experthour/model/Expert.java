package com.example.experthour.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "experts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expert {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String domain; // Java, DevOps, ML, etc.

    private Integer experience;
    private Double hourlyRate;
    private Double rating;

    private LocalDateTime createdAt;
}
