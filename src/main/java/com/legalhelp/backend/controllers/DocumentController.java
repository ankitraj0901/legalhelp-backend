package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.DocumentRequestDTO;
import com.legalhelp.backend.service.DocumentService;
import com.legalhelp.backend.service.DocumentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentServiceImpl documentServiceImpl;

    @PostMapping("/request")
    public ResponseEntity<?>requestDocument(
            @RequestBody DocumentRequestDTO request
            ){

        documentService.requestDocument(request.getAssignmentId(),
                request.getDocumentType(),
                request.getRequestedBy()
        );
        return ResponseEntity.ok("Document requested.!");
    }


    /* USER → View Requested Documents */
    @GetMapping("/requests/user/{userId}")
    public ResponseEntity<?>getRequestForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                documentService.getRequestsForUsers(userId)
        );
    }

    /* User -> Upload Document */
    @PostMapping("/upload")
    public ResponseEntity<?>uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("requestId") Long requestId
            ) {

        documentService.uploadDocument(requestId,file);
        return ResponseEntity.ok("Uploaded");
    }

    /* CA / LAWYER → View Uploaded Docs */
    @GetMapping("/assigned/{professionalId}")
    public ResponseEntity<?>getAssignedDocuments(
            @PathVariable  Long professionalId
    ) {

        return ResponseEntity.ok(
                documentService.getDocumentForProfessional(professionalId)
        );
    }

//    /*Getting uploaded document for Ca by clients*/
    @GetMapping("/ca/{professionalId}")
    ResponseEntity<?> getDocumentsForCa(@PathVariable Long professionalId) {
        return ResponseEntity.ok(
                documentServiceImpl.getUploadedDocumentsForCA(professionalId)
        );
    }

}
