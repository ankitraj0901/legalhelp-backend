package com.legalhelp.backend.repositories;

import com.legalhelp.backend.entity.DocumentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRequestRepository extends JpaRepository<DocumentRequest,Long> {

}
