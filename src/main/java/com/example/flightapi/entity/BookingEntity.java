/**
 * Flight Booking Entity
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private FlightEntity flight;

    @Column(name = "REFERENCE", nullable = false, length = 20)
    private String reference;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "BOOKING_TIME", nullable = false, updatable = false, insertable = false)
    private LocalDateTime bookingTime;

    @Column(name = "TOTAL_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "booking")
    private List<PassengerEntity> passengers;
}
