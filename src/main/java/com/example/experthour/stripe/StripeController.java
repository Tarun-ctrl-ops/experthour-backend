package com.example.experthour.stripe;

import com.example.experthour.dto.stripe.CreateCheckoutSessionRequest;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class StripeController {

    private final StripeService stripeService;

    @PostMapping("/checkout")
    public String createCheckout(@RequestBody CreateCheckoutSessionRequest req)
            throws Exception {

        Session session = stripeService.createCheckoutSession(req.bookingId());
        return session.getUrl();
    }
}
