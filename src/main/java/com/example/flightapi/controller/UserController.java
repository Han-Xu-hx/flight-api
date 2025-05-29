/**
 * User Controller
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/29.
 */
package com.example.flightapi.controller;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.common.BaseResponse;
import com.example.flightapi.dto.UserRegistrationRequest;
import com.example.flightapi.entity.UserEntity;
import com.example.flightapi.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api")
public class UserController {

    @Value("${jwt.secret-key}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Register a new user
     * 
     * @param request
     * @return
     */
    @RequestMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(
                            HttpStatus.BAD_REQUEST.value(),
                            "Email already exists.",
                            Map.of("errors", Map.of("email", request.getEmail() + " already exists."))));
        }

        // Create new user
        UserEntity newUser = new UserEntity();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setCountry(request.getCountry());
        newUser.setPhone(request.getPhone());

        // Save user to database
        UserEntity savedUser = userRepository.save(newUser);

        // Generate JWT token
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        String token = Jwts.builder()
                .subject(savedUser.getEmail())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                .signWith(key)
                .compact();

        return ResponseEntity.ok()
                .body(
                        BaseResponse.success(
                                Map.of(
                                        "token", token,
                                        "user", Map.of(
                                                "id", savedUser.getUserId(),
                                                "email", savedUser.getEmail(),
                                                "firstName", savedUser.getFirstName()))));
    }

    /**
     * Login a user
     * 
     * @param request
     * @return
     */
    @RequestMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserRegistrationRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail());

        // Check if user exists and password matches
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(
                            HttpStatus.BAD_REQUEST.value(),
                            "Invalid email or password.",
                            Map.of("errors", Map.of("email", "Invalid email or password."))));
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(
                            HttpStatus.BAD_REQUEST.value(),
                            "Invalid email or password.",
                            Map.of("errors", Map.of("pwd", "Invalid password."))));
        }

        // Generate JWT token
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        String token = Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                .signWith(key)
                .compact();

        // Return token and user details
        return ResponseEntity.ok()
                .body(
                        BaseResponse.success(
                                Map.of(
                                        "token", token,
                                        "user", Map.of(
                                                "id", user.getUserId(),
                                                "email", user.getEmail(),
                                                "firstName", user.getFirstName()))));
    }
}
