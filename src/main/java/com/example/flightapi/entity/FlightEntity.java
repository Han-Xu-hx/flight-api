/**
 * Flight Entity
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(nullable = false, name = "class_type_id", 
    referencedColumnName = "type_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private MasterClassTypeEntity classType;

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

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_AIRPORT_ID", nullable = false, referencedColumnName = "airport_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private AirportEntity departureAirport;

    @ManyToOne
    @JoinColumn(name = "DESTINATION_AIRPORT_ID", nullable = false, referencedColumnName = "airport_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private AirportEntity destinationAirport;

    @Column(name = "AIRLINE_CORP", nullable = false, length = 100)
    private String airlineCorp;

    @Column(name = "AIR_TYPE_NAME", nullable = false, length = 100)
    private String airTypeName;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "flight")
    private List<BookingEntity> bookings;
}
