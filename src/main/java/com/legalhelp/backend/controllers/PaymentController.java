package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.PaymentRequestDTO;
import com.legalhelp.backend.dto.PaymentVerifyRequest;
import com.legalhelp.backend.entity.PaymentRequest;
import com.legalhelp.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.legalhelp.backend.dto.RazorpayOrderResponse;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/request")
    public ResponseEntity<?> requestPayment(@RequestBody PaymentRequestDTO request){

        paymentService.requestPayment(request);

        return ResponseEntity.ok("Payment request created");
    }

    @PostMapping("/create-order/{paymentId}")
    public RazorpayOrderResponse createOrder(@PathVariable Long paymentId) throws Exception {
        return paymentService.createOrder(paymentId);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody PaymentVerifyRequest request){

        try {

            paymentService.verifyPayment(request);

            return ResponseEntity.ok("Payment successful");

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Payment verification failed: " + e.getMessage());
        }
    }

    @GetMapping("/client/{clientId}")
    public List<PaymentRequest> getClientPayments(@PathVariable Long clientId){
        return paymentService.getPaymentsForClient(clientId);
    }
}
