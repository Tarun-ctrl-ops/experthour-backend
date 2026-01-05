package com.example.experthour.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserResponse {

    private UUID id;
    private String name;
    private String email;
    private String role;
    private String skills;
    private Integer experience;
    private Double hourlyRate;
    private Double rating;
}
