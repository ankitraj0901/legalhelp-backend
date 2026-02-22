package com.legalhelp.backend.service;

import com.legalhelp.backend.dto.AssignedResponseDTO;
import com.legalhelp.backend.dto.UpdateAssignmentStatusRequest;
import com.legalhelp.backend.entity.AssignmentStatus;
import com.legalhelp.backend.entity.Assignments;
import com.legalhelp.backend.repositories.AssignmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class CaService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    // getting list of assignments for the professional
    public List<AssignedResponseDTO> getAllAssignmentForProfessional(Long professionalId) {
        return assignmentRepository.findByProfessional_UserId(professionalId).stream().map(assignments -> {
            AssignedResponseDTO response = new AssignedResponseDTO();
            response.setAssignmentId(assignments.getAssignmentId());
            response.setProfessionalId(assignments.getProfessional().getUserId());
            response.setClientId(assignments.getClint().getUserId());
            response.setName(assignments.getClint().getName());
            response.setEmail(assignments.getClint().getEmail());
            response.setRole(assignments.getClint().getRole());
            response.setTitle(assignments.getTitle());
            response.setDescription(assignments.getDescription());
            response.setAssignmentStatus(assignments.getAssignmentStatus());
            response.setServiceType(String.valueOf(assignments.getServiceType()));
            response.setDueDate(assignments.getDueDate());
            response.setUpdatedAt(assignments.getUpdatedAt());
            response.setAssignmentDate(assignments.getAssignedDate());
            return response;
        }).collect(Collectors.toList());
    }

//    Getting count of assignment assigned to the professional
    public int getAssignmentCount(Long professionalId) {
        return (int) assignmentRepository.countByProfessional_UserId(professionalId);
    }

    @Transactional
    public void updateAssignmentStatus(Long assignmentId, AssignmentStatus status) {
        Assignments assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(()-> new RuntimeException("Assignment Not Found.!"));

        assignment.setAssignmentStatus(status);
    }


}
