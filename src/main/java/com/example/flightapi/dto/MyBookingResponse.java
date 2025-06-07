/**
 * User FlightBooking Response DTO
 * Copyright (C) 2025
 * Created by hanxu on 2025/06/05.
 */
package com.example.flightapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

/**
 * User FlightBooking Response DTO
 */
@Data
public class MyBookingResponse {
    private Long bookingId;
    private String flightNumber;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String duration;
    private String status;
    private String totalPrice;
    private String reference;
    private List<PassengerDTO> passengers;
}
