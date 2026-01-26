package com.example.experthour.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentSessionResponse {
    private String sessionId;
    private String checkoutUrl;
    private String status;
}
