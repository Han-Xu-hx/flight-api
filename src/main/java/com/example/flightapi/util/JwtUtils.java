/**
 * Jwt Utils
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/26.
 */
package com.example.flightapi.util;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

/**
 * JwtUtils
 */
@Component
public class JwtUtils {

    private final String secretKey;
    private final long expirationMs;

    /**
     * Constructor
     * @param secretKey JWT Secret Key
     * @param expirationMs JWT Expiration Time in Milliseconds
     */
    public JwtUtils(
        @Value("${jwt.secret-key}") String secretKey,
        @Value("${jwt.expiration-ms}") long expirationMs) {
        
        // Check if the secret key is null or empty
        if (secretKey == null || secretKey.isBlank()) {
            throw new IllegalArgumentException("JWT Secret Key cannot be null or empty");
        }
        this.secretKey = secretKey;
        this.expirationMs = expirationMs;
    }

    /**
     * Generate a token for the user details
     * @param userDetails User Details
     * @return Token
     */
    public String generateToken(UserDetails userDetails) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expirationMs))
            .signWith(Keys.hmacShaKeyFor(keyBytes), Jwts.SIG.HS256)
            .compact();
    }

    /**
     * Extract the username from the token
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(keyBytes))
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    /**
     * Validate the token
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secretKey);
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Parse the token from the request header
     * @param request
     * @return
     */
    public String parseToken(HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}