package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.AssignedResponseDTO;
import com.legalhelp.backend.dto.CaResponseDTO;
import com.legalhelp.backend.dto.UpdateAssignmentStatusRequest;
import com.legalhelp.backend.service.CaService;
import com.legalhelp.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ca")
public class CaController {

    @Autowired
    private CaService caService;

//    Getting List of clients assigned to the current professional
    @GetMapping("/clients-list/{professionalId}")
    public ResponseEntity<List<AssignedResponseDTO>> getAssignmentForProfessional(@PathVariable Long professionalId) {
        return ResponseEntity.ok(caService.getAllAssignmentForProfessional(professionalId));
    }

    // getting how many(count) client assigned to the professional
    @GetMapping("/clients-count/{professionalId}")
    public ResponseEntity<Integer> getAssignmentCount(@PathVariable Long professionalId) {
        return ResponseEntity.ok(caService.getAssignmentCount(professionalId));
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> updateAssignmentStatus(
            @RequestBody UpdateAssignmentStatusRequest request) {

        caService.updateAssignmentStatus(
                request.getAssignmentId(),
                request.getStatus()
        );
        return ResponseEntity.ok("Assignment status updated.");
    }




}
