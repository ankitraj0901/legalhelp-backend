package com.legalhelp.backend.service;

import com.legalhelp.backend.dto.LoginRequest;
import com.legalhelp.backend.entity.User;
import com.legalhelp.backend.repositories.UserRepository;
import com.legalhelp.backend.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // load user to include info in token claims if needed
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole().name());
            claims.put("userId", user.getUserId());



            String token = jwtService.generateToken(user.getEmail(), claims);

            Map<String, Object> resp = new HashMap<>();
            resp.put("token", token);
            resp.put("message", "Login successful");
            resp.put("role", user.getRole());
            resp.put("userId", user.getUserId());
            resp.put("name", user.getName());

            return ResponseEntity.ok(resp);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}




//package com.legalhelp.backend.service;
//
//import com.legalhelp.backend.dto.LoginRequest;
//import com.legalhelp.backend.dto.LoginResponse;
//import com.legalhelp.backend.entity.User;
//import com.legalhelp.backend.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public ResponseEntity<?> login(LoginRequest request) {
//        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
//
//        if(user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found !");
//        }
//
//        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Password.");
//        }
//
//        return ResponseEntity.ok(
//                new LoginResponse("Login Successful", user)
//        );
//    }
//
//}
