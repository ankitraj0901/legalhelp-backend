package com.legalhelp.backend.repositories;

import com.legalhelp.backend.dto.UploadedDocumentDTO;
import com.legalhelp.backend.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    Optional<Document>findByRequestId(Long requestId);

    @Query("""
SELECT new com.legalhelp.backend.dto.UploadedDocumentDTO(
    d.id,
    dr.assignmentId,
    dr.documentType,
    d.fileUrl,
    d.uploadedAt
)
FROM Document d
JOIN DocumentRequest dr ON d.requestId = dr.id
WHERE dr.requestedBy = :professionalId
AND dr.status = 'UPLOADED'
ORDER BY d.uploadedAt DESC
""")
   List<UploadedDocumentDTO> findUploadedDocsForCA(Long professionalId);

}
