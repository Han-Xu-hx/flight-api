/**
 * Flight Entity
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "flights")
public class FlightEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "flight_id")
    private Long flightId;

    @Column(nullable = false, name = "flight_number", length = 10)
    private String flightNumber;

    @Column(nullable = false, name = "class_type_id")
    private Long classTypeId;

    @Column(nullable = false, name = "remaining")
    private Long remaining;
    
    @Column(nullable = false, name = "departure_date")
    private LocalDate departureDate;

    @Column(nullable = false, name = "departure_time")
    private LocalTime departureTime;

    @Column(nullable = false, name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(nullable = false, name = "ARRIVAL_TIME")
    private LocalTime arrivalTime;

    @Column(name = "DEPARTURE_AIRPORT_ID", nullable = false)
    private Long departureAirportId;

    @Column(name = "DESTINATION_AIRPORT_ID", nullable = false)
    private Long destinationAirportId;

    @Column(name = "AIRLINE_CORP", nullable = false, length = 100)
    private String airlineCorp;

    @Column(name = "AIR_TYPE_NAME", nullable = false, length = 100)
    private String airTypeName;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
