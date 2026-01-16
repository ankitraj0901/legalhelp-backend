package com.legalhelp.backend.service;

import com.legalhelp.backend.dto.AssignRequestDTO;
import com.legalhelp.backend.dto.AssignedResponseDTO;
import com.legalhelp.backend.dto.AssignmentDetailsDTO;
import com.legalhelp.backend.dto.AssignmentResponseDTO;
import com.legalhelp.backend.entity.Assignments;
import com.legalhelp.backend.entity.AssignmentStatus;
import com.legalhelp.backend.entity.User;
import com.legalhelp.backend.repositories.AssignmentRepository;
import com.legalhelp.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private AssignmentRepository assignmentRepository;

        // Creating Assignment for CA
        public AssignmentResponseDTO assignCa(AssignRequestDTO request) {

                // checking if the the CA is already assigned to the current user;
                Optional<Assignments> existingAssignment = assignmentRepository
                                .findByClint_UserIdAndProfessional_UserId(request.getClientId(),
                                                request.getProfessionalId());
                if (existingAssignment.isPresent()) {
                        return new AssignmentResponseDTO(existingAssignment.get(), true);
                }

                User clint = userRepository.findById(request.getClientId())
                                .orElseThrow(() -> new RuntimeException("Clint Not found"));

                User assignment = userRepository.findById(request.getProfessionalId())
                                .orElseThrow(() -> new RuntimeException("CA Not found.!"));

                Assignments assignments = new Assignments();
                assignments.setClint(clint);
                assignments.setProfessional(assignment);
                assignments.setAssignmentStatus(AssignmentStatus.AWAITING_DETAILS);

                assignmentRepository.save(assignments);

                return new AssignmentResponseDTO(assignments, false);
        }

        // Submitting extra details after CA Assigned to the user
        public void submitDetails(Long assignmentId, AssignmentDetailsDTO dto) {
                Assignments assignments = assignmentRepository.findById(assignmentId)
                                .orElseThrow(() -> new RuntimeException("Assignment Not Found!."));

                assignments.setTitle(dto.getTitle());
                assignments.setDescription(dto.getDescription());
                assignments.setServiceType(dto.getServiceType());
                assignments.setDueDate(dto.getDueDate());

                assignments.setAssignmentStatus(AssignmentStatus.ACTIVE);
                assignmentRepository.save(assignments);
        }

        // Assigning CA to the user if not assigned
        public AssignmentResponseDTO assignLawyer(AssignRequestDTO request) {

                // checking existing assignment;
                Optional<Assignments> existingAssignment = assignmentRepository
                                .findByClint_UserIdAndProfessional_UserId(request.getClientId(),
                                                request.getProfessionalId());

                if (existingAssignment.isPresent()) {
                        return new AssignmentResponseDTO(existingAssignment.get(), true);
                }

                User user = userRepository.findById(request.getClientId())
                                .orElseThrow(() -> new RuntimeException("User Not found!."));

                User lawyer = userRepository.findById(request.getProfessionalId())
                                .orElseThrow(() -> new RuntimeException("Lawyer Not found!."));

                Assignments assignments = new Assignments();
                assignments.setClint(user);
                assignments.setProfessional(lawyer);
                assignments.setAssignmentStatus(AssignmentStatus.AWAITING_DETAILS);

                assignmentRepository.save(assignments);
                return new AssignmentResponseDTO(assignments, false);
        }

        // Assigning consultant to current User if Not assigned
        public AssignmentResponseDTO assignConsultant(AssignRequestDTO request) {

                // checking existing assignment
                Optional<Assignments> existingAssignment = assignmentRepository
                                .findByClint_UserIdAndProfessional_UserId(request.getClientId(),
                                                request.getProfessionalId());

                if (existingAssignment.isPresent()) {
                        return new AssignmentResponseDTO(existingAssignment.get(), true);
                }

                User user = userRepository.findById(request.getClientId())
                                .orElseThrow(() -> new RuntimeException("User Not Found.!"));

                User consultant = userRepository.findById(request.getProfessionalId())
                                .orElseThrow(() -> new RuntimeException("Consultant Not found.!"));

                Assignments assignments = new Assignments();
                assignments.setClint(user);
                assignments.setProfessional(consultant);
                assignments.setAssignmentStatus(AssignmentStatus.AWAITING_DETAILS);

                assignmentRepository.save(assignments);
                return new AssignmentResponseDTO(assignments, false);
        }

        // finding how many client any CA have
        public List<Assignments> getAssignmentForProfessional(Long caId) {
                User ca = userRepository.findById(caId)
                                .orElseThrow(() -> new RuntimeException("CA Not Found.!"));
                return assignmentRepository.findByProfessional(ca);
        }

        // assigned CA to the user
        public List<AssignedResponseDTO> getAssignmentForClient(Long clientId) {
                return assignmentRepository.findByClint_UserId(clientId).stream().map(assignment -> {
                        AssignedResponseDTO response = new AssignedResponseDTO();
                        response.setAssignmentId(assignment.getAssignmentId());
                        response.setClientId(assignment.getClint().getUserId());
                        response.setProfessionalId(assignment.getProfessional().getUserId());
                        response.setName(assignment.getProfessional().getName());
                        response.setEmail(assignment.getProfessional().getEmail());
                        response.setRole(assignment.getProfessional().getRole());
                        response.setTitle(assignment.getTitle());
                        response.setDescription(assignment.getDescription());
                        response.setDueDate(assignment.getDueDate());
                        response.setAssignmentStatus(assignment.getAssignmentStatus());
                        response.setUpdatedAt(assignment.getUpdatedAt());

                        return response;
                }).collect(Collectors.toList());
        }

}
