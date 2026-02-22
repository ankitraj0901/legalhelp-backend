package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.AssignRequestDTO;
import com.legalhelp.backend.dto.AssignedResponseDTO;
import com.legalhelp.backend.dto.AssignmentDetailsDTO;
import com.legalhelp.backend.dto.AssignmentResponseDTO;
import com.legalhelp.backend.entity.Assignments;
import com.legalhelp.backend.entity.User;
import com.legalhelp.backend.repositories.AssignmentRepository;
import com.legalhelp.backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentRepository assignmentRepository;


    //Assigning CA
    @PostMapping("/assign-ca")
    public ResponseEntity<AssignmentResponseDTO> assignCa(@RequestBody AssignRequestDTO request) {
        return ResponseEntity.ok(assignmentService.assignCa(request));
    }

    //Assigning Lawyer
    @PostMapping("/assign-lawyer")
    public ResponseEntity<AssignmentResponseDTO> assignLawyer(@RequestBody AssignRequestDTO request) {
        return ResponseEntity.ok(assignmentService.assignLawyer(request));
    }

    //Assigning Consultant
    @PostMapping("/assign-consultant")
    public ResponseEntity<AssignmentResponseDTO> assignConsultant(@RequestBody AssignRequestDTO request) {
        return ResponseEntity.ok(assignmentService.assignConsultant(request));
    }

    // how many professional is assigned to the current loggedin User!.
    @GetMapping("/assigned-professional/{clientId}")
    public ResponseEntity<?> assignedProfessional(@PathVariable Long clientId) {
        return ResponseEntity.ok(assignmentService.getAssignmentForClient(clientId));
    }

    //After client connted to professional user need the sent the case details so professional can help the user
    @PutMapping("/submit-details/{assignmentId}")
    public ResponseEntity<?> submitDetails(
            @PathVariable Long assignmentId,
            @RequestBody AssignmentDetailsDTO dto
    ){
        assignmentService.submitDetails(assignmentId,dto);
        return ResponseEntity.ok("Details Submitted!");
    }


}
