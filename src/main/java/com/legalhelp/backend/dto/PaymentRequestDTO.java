package com.legalhelp.backend.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {

    private Long assignmentId;
    private Long clientId;
    private Long professionalId;
    private Double amount;
    private String message;

}
