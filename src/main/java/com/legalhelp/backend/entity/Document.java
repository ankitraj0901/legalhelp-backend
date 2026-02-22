package com.legalhelp.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestId;
    private String fileName;
    private String fileUrl;
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
