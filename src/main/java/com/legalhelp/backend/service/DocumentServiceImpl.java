package com.legalhelp.backend.service;

import com.legalhelp.backend.dto.UploadedDocumentDTO;
import com.legalhelp.backend.entity.Assignments;
import com.legalhelp.backend.entity.Document;
import com.legalhelp.backend.entity.DocumentRequest;
import com.legalhelp.backend.repositories.AssignmentRepository;
import com.legalhelp.backend.repositories.DocumentRepository;
import com.legalhelp.backend.repositories.DocumentRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.legalhelp.backend.service.FileStorageService;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRequestRepository documentRequestRepository;
    private final DocumentRepository documentRepository;
    private final AssignmentRepository assignmentRepository;
    private final FileStorageService fileStorageService;


    @Override
    public void requestDocument(Long assignmentId, String documentType, Long professionalId) {
        DocumentRequest request = new DocumentRequest();
        request.setAssignmentId(assignmentId);
        request.setDocumentType(documentType);
        request.setRequestedBy(professionalId);
        request.setStatus("PENDING");

        documentRequestRepository.save(request);
    }

    @Override
    public List<DocumentRequest> getRequestsForUsers(Long userId) {

        List<Long>assignmentIds = assignmentRepository.findByClint_UserId(userId)
                .stream()
                .map(Assignments::getAssignmentId)
                .toList();

        return documentRequestRepository.findAll().stream()
                .filter(req -> assignmentIds.contains(req.getAssignmentId()))
                .toList();
    }

    @Override
    public void uploadDocument(Long requestId, MultipartFile file) {
        //requestId == professionalId

        DocumentRequest request = documentRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Invalid Document Request.!"));

        String fileUrl = fileStorageService.save(file);

        Document document = new Document();
        document.setRequestId(requestId);
        document.setFileName(file.getOriginalFilename());
        document.setFileUrl(fileUrl);

        documentRepository.save(document);

        request.setStatus("UPLOADED");
        documentRequestRepository.save(request);
    }

    @Override
    public List<DocumentRequest> getDocumentForProfessional(Long professionalId) {
        return documentRequestRepository.findAll().stream()
                .filter(req-> req.getRequestedBy().equals(professionalId))
                .toList();
    }


    public List<UploadedDocumentDTO> getUploadedDocumentsForCA(Long professionalId) {
        return documentRepository.findUploadedDocsForCA(professionalId);
    }
}
