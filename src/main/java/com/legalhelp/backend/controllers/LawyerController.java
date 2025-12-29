package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.LawyerResponseDTO;
import com.legalhelp.backend.service.LawyerService;
import com.legalhelp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lawyer")
public class LawyerController {

    @Autowired
    LawyerService lawyerService;

    @GetMapping("/clients-list/{professionalId}")
    public ResponseEntity<?> getAllClients(@PathVariable Long professionalId) {
        return ResponseEntity.ok(lawyerService.getAllClients(professionalId));
    }




}
