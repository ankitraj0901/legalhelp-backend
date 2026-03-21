package com.legalhelp.backend.service;

import com.legalhelp.backend.dto.PaymentRequestDTO;
import com.legalhelp.backend.dto.PaymentVerifyRequest;
import com.legalhelp.backend.dto.RazorpayOrderResponse;
import com.legalhelp.backend.entity.PaymentRequest;
import com.legalhelp.backend.entity.PaymentStatus;
import com.legalhelp.backend.repositories.PaymentRequestRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//import static java.security.KeyRep.Type.SECRET;

@Service
public class PaymentService {

    @Autowired
    private RazorpayClient razorpayClient;

    @Autowired
    private PaymentRequestRepository paymentRepository;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public void requestPayment(PaymentRequestDTO dto){

        PaymentRequest pr = new PaymentRequest();

        pr.setAssignmentId(dto.getAssignmentId());
        pr.setClientId(dto.getClientId());
        pr.setProfessionalId(dto.getProfessionalId());
        pr.setAmount(dto.getAmount());
        pr.setMessage(dto.getMessage());

        pr.setStatus(PaymentStatus.REQUESTED);

        pr.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(pr);
    }

    public RazorpayOrderResponse createOrder(Long paymentRequestId) throws Exception {

        PaymentRequest pr = paymentRepository.findById(paymentRequestId)
                .orElseThrow();

        JSONObject options = new JSONObject();
        options.put("amount", pr.getAmount() * 100);
        options.put("currency", "INR");
        options.put("receipt", "payment_" + pr.getId());

        Order order = razorpayClient.orders.create(options);

        pr.setRazorpayOrderId(order.get("id"));
        pr.setStatus(PaymentStatus.ORDER_CREATED);

        paymentRepository.save(pr);

        return new RazorpayOrderResponse(order.get("id"), pr.getAmount());
    }


    public void verifyPayment(PaymentVerifyRequest request) throws Exception {

        JSONObject attributes = new JSONObject();

        attributes.put("razorpay_order_id", request.getOrderId());
        attributes.put("razorpay_payment_id", request.getPaymentId());
        attributes.put("razorpay_signature", request.getSignature());

        Utils.verifyPaymentSignature(attributes, razorpayKeySecret);

        PaymentRequest pr =
                paymentRepository.findByRazorpayOrderId(request.getOrderId());

        if(pr == null){
            throw new RuntimeException("Payment request not found");
        }

        if(pr.getStatus() == PaymentStatus.PAID){
            return;
        }

        pr.setStatus(PaymentStatus.PAID);
        pr.setRazorpayPaymentId(request.getPaymentId());
        pr.setPaidAt(LocalDateTime.now());

        paymentRepository.save(pr);
    }

    public List<PaymentRequest> getPaymentsForClient(Long clientId){
        return paymentRepository.findByClientId(clientId);
    }
}
