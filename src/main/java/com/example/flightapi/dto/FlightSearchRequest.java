/**
 * Flight Search Request DTO
 * Copyright (C) 2025
 * Created by hanxu on 2025/06/05.
 */
package com.example.flightapi.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * Flight Search Request DTO
 */
@Data
public class FlightSearchRequest {

    private String departure;
    private String arrival;
    private LocalDate date;
    private LocalDate returnDate;
    private String cabinClass;
    private String tripType;
    private Long passengers;
}
