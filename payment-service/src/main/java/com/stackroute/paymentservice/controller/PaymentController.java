package com.stackroute.paymentservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import javax.validation.Valid;
@RestController
@RequestMapping("/payments")
public class PaymentController {
//
//    @Value("${stripe.publicKey}")
//    private String publicKey;
//
//    @Autowired
//    private StripeService stripeService; // You may need to create a service to handle payments
//
//    @GetMapping("/checkout")
//    public String showCheckoutPage(Model model) {
//        model.addAttribute("publicKey", publicKey);
//        return "checkout";
//    }
//
//    @PostMapping("/create-payment-intent")
//    @ResponseBody
//    public String createPaymentIntent(@RequestParam("amount") Integer amount) {
//        try {
//            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//                    .setAmount((long) amount * 100) // Stripe expects the amount in cents
//                    .setCurrency("usd")
//                    .build();
//
//            PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//            return paymentIntent.getClientSecret();
//        } catch (StripeException e) {
//            e.printStackTrace();
//            return e.getMessage();
//        }
//    }
//
//    @PostMapping("/process-payment")
//    public String processPayment(@RequestParam("paymentIntentId") String paymentIntentId, Model model) {
//        try {
//            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
//            PaymentIntent confirmIntent = paymentIntent.confirm();
//
//            // You can check the status of the payment and handle success or failure accordingly
//            if ("succeeded".equals(confirmIntent.getStatus())) {
//                model.addAttribute("message", "Payment successful!");
//            } else {
//                model.addAttribute("message", "Payment failed.");
//            }
//
//            return "payment-result";
//        } catch (StripeException e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }



    // create a Gson object
    private static Gson gson = new Gson();

//    @PostMapping("/payment")
//    /**
//     * Payment with Stripe checkout page
//     *
//     * @throws StripeException
//     */
//    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
//        // We initilize stripe object with the api key
//        init();
//        // We create a  stripe session parameters
//        SessionCreateParams params = SessionCreateParams.builder()
//                // We will use the credit card payment method
//                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
//                .setCancelUrl(
//                        payment.getCancelUrl())
//                .addLineItem(
//                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
//                                .setPriceData(
//                                        SessionCreateParams.LineItem.PriceData.builder()
//                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
//                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
//                                                        .builder().setName(payment.getName()).build())
//                                                .build())
//                                .build())
//                .build();
//        // create a stripe session
//        Session session = Session.create(params);
//        Map<String, String> responseData = new HashMap<>();
//        // We get the sessionId and we putted inside the response data you can get more info from the session object
//        responseData.put("id", session.getId());
//        // We can return only the sessionId as a String
//        return gson.toJson(responseData);
//    }

//    @PostMapping("/create-payment-intent")
//    public ResponseEntity<String> createPaymentIntent(@RequestBody List<Product> products) {
//        try {
//            // Set your Stripe API key
//            init();
//
//            // Calculate the total amount based on the products
//            long totalAmount = calculateTotalAmount(products);
//
//            // Create a Payment Intent on Stripe
//            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//                    .setAmount(totalAmount)
//                    .setCurrency("inr")
//                    .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
//                    .setConfirm(true)
//                    .setPaymentMethodTypes(List.of("card"))
//                    .setReceiptEmail("customer@example.com")
//                    .build();
//
//            PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//            // Handle the paymentIntent as needed
//            if ("succeeded".equals(paymentIntent.getStatus())) {
//                // Payment succeeded
//                return ResponseEntity.ok("Payment succeeded!");
//            } else {
//                // Payment requires further action, such as authentication
//                // You may need to return additional information to your Angular frontend
//                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Payment requires further action.");
//            }
//        } catch (StripeException e) {
//            // Handle Stripe-related exceptions
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing payment: " + e.getMessage());
//        }
//    }

    // Calculate the total amount based on the products
//    private long calculateTotalAmount(List<Product> products) {
//        long totalAmount = 0;
//        for (Product product : products) {
//            // Assuming product.getPrice() returns the price in cents
//            totalAmount += product.getAm.getPrice();
//        }
//        return totalAmount;
//    }


    @PostMapping("/create-payment-session")
    public String paymentWithCheckoutPage(@RequestBody List<CheckoutPayment> payments) throws StripeException {
        // Initialize Stripe with your secret key
        init();

        // Create a list of line items for the session
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (CheckoutPayment payment : payments) {
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(payment.getQuantity())
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency(payment.getCurrency())
                                    .setUnitAmount(payment.getAmount())
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(payment.getName())
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();
            lineItems.add(lineItem);
        }

        // Create a session with the line items
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(payments.get(0).getSuccessUrl()) // Assuming the success URL is the same for all payments
                .setCancelUrl(payments.get(0).getCancelUrl())
                .addAllLineItem(lineItems)
                .build();

        // Create the Stripe session
        Session session = Session.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());

        // Return the session ID as a JSON response
        return gson.toJson(responseData);
    }
    private static void init() {
        Stripe.apiKey = "sk_test_51N0qhWSJ9eI0rlVpF8p5Kfxvxm9sqZYqG7p5ZwCQy35A343hjDEDVXH52MYtRGvO9aA2BhJmAnAmsLafRTGeduYv00RvE6csNP";
    }
}