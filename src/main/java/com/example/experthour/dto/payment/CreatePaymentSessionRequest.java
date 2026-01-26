package com.example.experthour.dto.payment;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePaymentSessionRequest {
    private Long expertId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double amount;
    private String successUrl;
    private String cancelUrl;
}
