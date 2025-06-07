/**
 * JwtProperties
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/26.
 */
package com.example.flightapi.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * JwtProperties
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private long expirationMs;
}