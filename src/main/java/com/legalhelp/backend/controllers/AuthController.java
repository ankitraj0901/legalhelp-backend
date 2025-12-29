package com.legalhelp.backend.controllers;



import com.legalhelp.backend.dto.LoginRequest;
import com.legalhelp.backend.dto.RegistrationRequest;
import com.legalhelp.backend.service.LoginService;
import com.legalhelp.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully!");
    }



    @PostMapping("/login")
    public ResponseEntity<?>login (@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}

