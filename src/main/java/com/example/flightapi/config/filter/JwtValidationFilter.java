/**
 * User Controller
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/29.
 */
package com.example.flightapi.config.filter;

import java.util.ArrayList;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.flightapi.util.JwtUtils;
import com.example.flightapi.util.SecurityProperties;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final SecurityProperties securityProperties;

    /**
     * Filter to validate JWT token in the request header.
     * If the token is valid, the user is authenticated and the request is allowed to proceed.
     * If the token is invalid or missing, the request is rejected with a 401 error.
     * @param request the http request
     * @param response the http response
     * @param filterChain the filter chain to proceed with the request
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        // Allow CORS requests
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // Allow requests to the allowed paths
        boolean isAllowedPath = securityProperties.getAllowedPaths().stream()
                .anyMatch(path -> request.getRequestURI().startsWith(
                    path != null ? path.replace("*", "") : path));

        // If the request is allowed, proceed with the filter chain
        if (isAllowedPath) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extract the JWT token from the request header and validate it
            String token = jwtUtils.parseToken(request);
            if (token == null || !jwtUtils.validateToken(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "invalid token");
                return;
            }

            // Extract the username from the JWT token and create a new authentication object
            String username = jwtUtils.extractUsername(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
                    new ArrayList<>());
            
            // Set the authentication object in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {

            // If any exception occurs, reject the request with a 401 error
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
            return;
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }
}