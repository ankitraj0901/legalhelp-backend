package com.legalhelp.backend.dto;

import lombok.Data;

@Data
public class RazorpayOrderResponse {

    private String orderId;
    private Double amount;

    public RazorpayOrderResponse(String orderId, Double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}
