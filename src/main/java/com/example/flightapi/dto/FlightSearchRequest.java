package com.example.flightapi.dto;

import java.time.LocalDate;

import lombok.Data;

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
