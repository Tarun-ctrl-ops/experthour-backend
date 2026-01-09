package com.example.experthour.expert;

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

    private String name;
    private String title;
    private String bio;
    private String skills;

    private double pricePerHour;

    // Availability
    private String availableFrom;
    private String availableTo;
}
