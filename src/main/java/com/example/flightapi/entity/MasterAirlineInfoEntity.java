/**
 * Airline information Entity
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "master_airline_infos")
public class MasterAirlineInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AIRLINE_ID")
    private Long airlineId;

    @Column(name = "AIRLINE_CORP", nullable = false, length = 100)
    private String airlineCorp;

    @Column(name = "FLIGHT_NUMBER", nullable = false, length = 10, unique = true)
    private String flightNumber;

    @Column(name = "DEPARTURE_AIRPORT_ID", nullable = false)
    private Long departureAirportId;

    @Column(name = "DESTINATION_AIRPORT_ID", nullable = false)
    private Long destinationAirportId;

    @Column(name = "AIR_NAME", nullable = false, length = 50)
    private String airName;

    @Column(name = "C_SEATS_CNT", nullable = false)
    private Long cSeatsCnt;

    @Column(name = "B_SEATS_CNT", nullable = false)
    private Long bSeatsCnt;

    @Column(name = "F_SEATS_CNT", nullable = false)
    private Long fSeatsCnt;

    @Column(name = "C_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal cPrice;

    @Column(name = "B_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal bPrice;
    
    @Column(name = "F_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal fPrice;
}