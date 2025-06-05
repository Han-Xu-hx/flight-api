/**
 * Flight DTO
 * Copyright (C) 2025
 * Created by hanxu on 2025/06/05.
 */
package com.example.flightapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Flight DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private Long id;
    private String airline;
    private String flightNo;
    private String departure;
    private String departureAirport;
    private String arrival;
    private String arrivalAirport;
    private LocalDate date;
    private LocalTime time;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String duration;
    private BigDecimal price;
    private Long remainingSeats;
    private String cabinClass;
}
