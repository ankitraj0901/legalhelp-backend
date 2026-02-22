package com.legalhelp.backend.dto;

import java.time.LocalDateTime;

public record UploadedDocumentDTO (
    Long documentId,
    Long assignmentId,
    String documentType,
    String fileUrl,
    LocalDateTime uploadedAt
){}
