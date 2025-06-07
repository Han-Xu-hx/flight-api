/**
 * Passenger Request DTO
 * Copyright (C) 2025
 * Created by hanxu on 2025/06/05.
 */
package com.example.flightapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Passenger Request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest {
    private String firstName;
    private String lastName;
    private String email;
}
