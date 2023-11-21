//package com.stackroute.paymentservice.service;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
//import com.stripe.param.PaymentIntentCreateParams;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StripeService {
//
//    @Value("${stripe.secretKey}")
//    private String secretKey;
//
//    public StripeService() {
//        Stripe.apiKey = secretKey;
//    }
//
//    public String createPaymentIntent(Integer amount) throws StripeException {
//        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//                .setAmount((long) amount * 100) // Stripe expects the amount in cents
//                .setCurrency("usd")
//                .build();
//
//        PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//        return paymentIntent.getClientSecret();
//    }
//
//    // Add more methods to handle other Stripe-related operations as needed
//}
