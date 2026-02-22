package com.legalhelp.backend.dto;


import lombok.Data;

@Data
public class DocumentRequestDTO {
    private Long assignmentId;
    private String documentType;
    private Long requestedBy;
}
