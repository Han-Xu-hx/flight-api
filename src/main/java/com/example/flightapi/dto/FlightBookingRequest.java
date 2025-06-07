/**
 * Flight Booking Request DTO
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/29.
 */
package com.example.flightapi.dto;

import java.util.List;

import lombok.Data;

/**
 * Flight Booking Request DTO
 */
@Data
public class FlightBookingRequest {

    private Long userId;
    private FlightDTO booking;
    private List<PassengerRequest> passengers;
    private String reference;
}
