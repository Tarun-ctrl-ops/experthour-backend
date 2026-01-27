package com.example.experthour.stripe;

import com.example.experthour.booking.Booking;
import com.example.experthour.booking.BookingRepository;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripeService {

    private final BookingRepository bookingRepository;

    public Session createCheckoutSession(Long bookingId) throws Exception {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow();

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("https://experthour-frontend.vercel.app/booking-success")
                        .setCancelUrl("https://experthour-frontend.vercel.app/experts")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("inr")
                                                        .setUnitAmount(
                                                                (long) (booking.getExpert().getPricePerHour() * 100)
                                                        )
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName("Expert Session with " + booking.getExpert().getName())
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .putMetadata("bookingId", booking.getId().toString())
                        .build();

        return Session.create(params);
    }
}
