package com.legalhelp.backend.repositories;

import com.legalhelp.backend.entity.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {

    List<PaymentRequest> findByClientId(Long clientId);

    List<PaymentRequest> findByAssignmentId(Long assignmentId);

    PaymentRequest findByRazorpayOrderId(String razorpayOrderId);


}
