package com.example.experthour.expert;

import com.example.experthour.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "experts")
@Getter
@Setter
@NoArgsConstructor
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExpertStatus status;

    private String name;
    private String title;
    private String bio;
    private String skills;

    private double pricePerHour;

    // Availability
    private String availableFrom;
    private String availableTo;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(columnDefinition = "TEXT")
    private String availabilityJson;


}
