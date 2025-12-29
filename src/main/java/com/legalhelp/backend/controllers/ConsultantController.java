package com.legalhelp.backend.controllers;

import com.legalhelp.backend.dto.ConsultantResponseDTO;
import com.legalhelp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultant")
public class ConsultantController {

    @Autowired
    private UserService userService;


}
