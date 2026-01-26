package com.example.experthour.payment;

import com.example.experthour.dto.payment.CreatePaymentSessionRequest;
import com.example.experthour.dto.payment.PaymentSessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-session")
    public PaymentSessionResponse createCheckoutSession(
            @RequestBody CreatePaymentSessionRequest request
    ) {
        return paymentService.createStripeSession(request);
    }

    @GetMapping("/verify/{sessionId}")
    public PaymentSessionResponse verifyPayment(@PathVariable String sessionId) {
        return paymentService.verifySession(sessionId);
    }

    @PostMapping("/webhook")
    public void handleStripeWebhook(@RequestBody String payload,
                                    @RequestHeader("Stripe-Signature") String signature) {
        paymentService.handleWebhook(payload, signature);
    }
}
