/**
 * Security Properties
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/25.
 */
package com.example.flightapi.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Security Properties
 */
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {
    private List<String> allowedPaths = new ArrayList<String>();
}
