package com.example.experthour.payment;

import com.example.experthour.booking.Booking;
import com.example.experthour.booking.BookingRepository;
import com.example.experthour.dto.payment.CreatePaymentSessionRequest;
import com.example.experthour.dto.payment.PaymentSessionResponse;
import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Stripe Payment Integration Service
 *
 * SETUP INSTRUCTIONS:
 * 1. Add Stripe dependency to pom.xml:
 *    <dependency>
 *        <groupId>com.stripe</groupId>
 *        <artifactId>stripe-java</artifactId>
 *        <version>24.0.0</version>
 *    </dependency>
 *
 * 2. Add to application.properties:
 *    stripe.api.key=sk_test_YOUR_KEY_HERE
 *    stripe.webhook.secret=whsec_YOUR_SECRET_HERE
 *
 * 3. Uncomment Stripe API code below after adding dependency
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${stripe.api.key:}")
    private String stripeApiKey;

    @Value("${stripe.webhook.secret:}")
    private String webhookSecret;

    private final ExpertRepository expertRepository;
    private final BookingRepository bookingRepository;

    public PaymentSessionResponse createStripeSession(CreatePaymentSessionRequest request) {

        Expert expert = expertRepository.findById(request.getExpertId())
                .orElseThrow(() -> new RuntimeException("Expert not found"));

        // TODO: Uncomment after adding Stripe dependency
        /*
        try {
            Stripe.apiKey = stripeApiKey;

            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(request.getSuccessUrl())
                    .setCancelUrl(request.getCancelUrl())
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("inr")
                                                    .setUnitAmount((long) (expert.getPricePerHour() * 100)) // Amount in paise
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("1-hour consultation with " + expert.getName())
                                                                    .setDescription(expert.getTitle())
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .putMetadata("expertId", String.valueOf(request.getExpertId()))
                    .putMetadata("startTime", request.getStartTime().toString())
                    .putMetadata("endTime", request.getEndTime().toString())
                    .build();

            Session session = Session.create(params);

            return new PaymentSessionResponse(
                    session.getId(),
                    session.getUrl(),
                    "created"
            );

        } catch (StripeException e) {
            throw new RuntimeException("Failed to create payment session: " + e.getMessage());
        }
        */

        // MOCK RESPONSE (Remove when Stripe is integrated)
        return new PaymentSessionResponse(
                "cs_test_mock_session_id",
                "https://checkout.stripe.com/pay/mock_session",
                "created"
        );
    }

    public PaymentSessionResponse verifySession(String sessionId) {

        // TODO: Uncomment after adding Stripe dependency
        /*
        try {
            Stripe.apiKey = stripeApiKey;
            Session session = Session.retrieve(sessionId);

            if ("complete".equals(session.getPaymentStatus())) {
                // Create booking after successful payment
                Long expertId = Long.parseLong(session.getMetadata().get("expertId"));
                LocalDateTime startTime = LocalDateTime.parse(session.getMetadata().get("startTime"));
                LocalDateTime endTime = LocalDateTime.parse(session.getMetadata().get("endTime"));

                // Create booking logic here

                return new PaymentSessionResponse(
                        session.getId(),
                        session.getUrl(),
                        "paid"
                );
            }

            return new PaymentSessionResponse(
                    session.getId(),
                    session.getUrl(),
                    session.getPaymentStatus()
            );

        } catch (StripeException e) {
            throw new RuntimeException("Failed to verify payment: " + e.getMessage());
        }
        */

        // MOCK RESPONSE
        return new PaymentSessionResponse(sessionId, null, "paid");
    }

    public void handleWebhook(String payload, String signature) {

        // TODO: Uncomment after adding Stripe dependency
        /*
        try {
            Event event = Webhook.constructEvent(payload, signature, webhookSecret);

            if ("checkout.session.completed".equals(event.getType())) {
                Session session = (Session) event.getDataObjectDeserializer()
                        .getObject()
                        .orElse(null);

                if (session != null && "paid".equals(session.getPaymentStatus())) {
                    // Process successful payment
                    Long expertId = Long.parseLong(session.getMetadata().get("expertId"));
                    LocalDateTime startTime = LocalDateTime.parse(session.getMetadata().get("startTime"));
                    LocalDateTime endTime = LocalDateTime.parse(session.getMetadata().get("endTime"));

                    // Create booking here
                    System.out.println("Payment successful for expert: " + expertId);
                }
            }

        } catch (SignatureVerificationException e) {
            throw new RuntimeException("Invalid webhook signature");
        }
        */

        System.out.println("Webhook received (mock implementation)");
    }
}
