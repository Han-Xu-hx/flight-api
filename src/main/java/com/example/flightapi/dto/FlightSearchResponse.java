package com.example.flightapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class FlightSearchResponse {

    private Long id;
    private String airline;
    private String flightNo;
    private String departure;
    private String arrival;
    private LocalDate date;
    private LocalTime time;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String duration;
    private BigDecimal price;
    private Long remainingSeats;
    private String cabinClass;
}
