package com.example.flightapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class FlightSearchView {
    private Long flightId;
    private String airlineCorp;
    private String flightNumber;
    private String typeName;
    private Long remaining;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureAirportCity;
    private String destinationAirportCode;
    private String destinationAirportName;
    private String destinationAirportCity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private BigDecimal price;
}
