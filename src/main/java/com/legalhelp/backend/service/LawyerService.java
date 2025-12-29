package com.legalhelp.backend.service;

import com.legalhelp.backend.dto.AssignedResponseDTO;
import com.legalhelp.backend.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LawyerService {

    AssignmentRepository assignmentRepository;

    public List<AssignedResponseDTO> getAllClients(Long professionalId) {
            return assignmentRepository.findByProfessional_UserId(professionalId).stream().map(assignments -> {
            AssignedResponseDTO response = new AssignedResponseDTO();
            response.setAssignmentId(assignments.getAssignmentId());
            response.setClientId(assignments.getClint().getUserId());
            response.setProfessionalId(assignments.getProfessional().getUserId());
            response.setName(assignments.getClint().getName());
            response.setEmail(assignments.getClint().getEmail());
            response.setRole(assignments.getClint().getRole());
            response.setTitle(assignments.getTitle());
            response.setServiceType(String.valueOf(assignments.getServiceType()));
            response.setAssignmentStatus(assignments.getAssignmentStatus());
            response.setDescription(assignments.getDescription());
            response.setAssignmentDate(assignments.getAssignedDate());
            response.setDueDate(assignments.getDueDate());
            response.setUpdatedAt(assignments.getUpdatedAt());

            return response;
        }).collect(Collectors.toList());
    }
}
