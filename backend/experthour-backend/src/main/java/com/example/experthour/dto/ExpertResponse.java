package com.example.experthour.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ExpertResponse {

    private UUID id;
    private String name;
    private String email;
    private String domain;
    private Integer experience;
    private Double hourlyRate;
    private Double rating;
}