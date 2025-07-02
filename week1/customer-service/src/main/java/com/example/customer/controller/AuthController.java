package com.example.customer.controller;

import com.example.customer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    private final String USERNAME = "user";
    private final String PASSWORD = "password";

    // DTO for authentication request
    public static class AuthRequest {
        private String username;
        private String password;
    
        // Default constructor
        public AuthRequest() {}
    
        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            String token = jwtUtil.generateToken(username);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    public AuthController() {
        System.out.println("AuthController created!");
    }

    @GetMapping("/test")
    public String test() {
    System.out.println("Test endpoint hit!");
    return "OK";
    }

    @PostMapping("/echo")
    public AuthRequest echo(@RequestBody AuthRequest authRequest) {
    System.out.println("ECHO: " + authRequest.getUsername() + ", " + authRequest.getPassword());
    return authRequest;
    }
} 