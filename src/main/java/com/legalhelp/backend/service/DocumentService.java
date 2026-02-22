package com.legalhelp.backend.service;

import com.legalhelp.backend.entity.DocumentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface DocumentService {

    void requestDocument(Long assignmentId,String documentType,Long professionalId);

    List<DocumentRequest>getRequestsForUsers(Long userId);

    void uploadDocument(Long requestId, MultipartFile file);

    List<DocumentRequest> getDocumentForProfessional(Long professionalId);
}
