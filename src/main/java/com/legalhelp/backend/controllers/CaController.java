package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.AssignedResponseDTO;
import com.legalhelp.backend.dto.CaResponseDTO;
import com.legalhelp.backend.service.CaService;
import com.legalhelp.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // get all client deadlines from database;
    //Incomplete
//    @GetMapping("clients-deadline/{professionalId}")
//    public ResponseEntity<?> getAssignmentDeadlines(@PathVariable Long professionalId) {
//        return ResponseEntity.ok(caService);
//    }

    //get all new clients(assigned today)
//    @GetMapping("new-clients/{professionalid}")
////    public ResponseEntity<?> getAllNewClients(@PathVariable Long professionalId) {
////        return ResponseEntity.ok(caService)
////    }


}
