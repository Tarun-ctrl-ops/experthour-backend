package com.example.experthour.stripe;

import com.example.experthour.booking.BookingRepository;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.example.experthour.booking.BookingStatus;


@RestController
@RequestMapping("/api/payments/webhook")
@RequiredArgsConstructor
public class StripeWebhookController {

    private final BookingRepository bookingRepository;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping
    public void handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ) throws Exception {

        Event event = Webhook.constructEvent(
                payload, sigHeader, endpointSecret
        );

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer()
                    .getObject()
                    .orElseThrow();

            Long bookingId = Long.valueOf(session.getMetadata().get("bookingId"));

            bookingRepository.findById(bookingId).ifPresent(b -> {
                b.setStatus(BookingStatus.PAID);
                bookingRepository.save(b);
            });
        }
    }
}
