package com.example.experthour.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExpertRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Domain is required")
    private String domain;

    private Integer experience;
    private Double hourlyRate;
}
