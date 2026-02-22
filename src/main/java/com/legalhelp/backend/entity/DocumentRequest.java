package com.legalhelp.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "document_requests")
@Data
public class DocumentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assignmentId;
    private Long requestedBy;
    private String documentType;
    private String status;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
