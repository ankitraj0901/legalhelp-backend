package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.CaResponseDTO;
import com.legalhelp.backend.dto.ConsultantResponseDTO;
import com.legalhelp.backend.dto.LawyerResponseDTO;
import com.legalhelp.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ca-list/{clientId}")
    public ResponseEntity<List<CaResponseDTO>> getAllCAs(@PathVariable Long clientId) {
        return ResponseEntity.ok(userService.getAllCAs(clientId));
    }

    //fetch all the CA and check if the current user is assigned to any CA if yes then mark that CA field true
    @GetMapping("/lawyer-list/{clientId}")
    public ResponseEntity<List<LawyerResponseDTO>> getAllLawyer(@PathVariable Long clientId) {
        return ResponseEntity.ok(userService.getAllLawyers(clientId));
    }

    @GetMapping("/consultant-list/{clientId}")
    public ResponseEntity<List<ConsultantResponseDTO>> getAllConsultant(@PathVariable Long clientId) {
        return ResponseEntity.ok(userService.getAllConsultant(clientId));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Ok");
    }
}
