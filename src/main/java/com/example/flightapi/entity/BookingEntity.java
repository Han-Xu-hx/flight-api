/**
 * Flight Booking Entity
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FLIGHT_ID")
    private Long flightId;

    @Column(name = "REFERENCE", nullable = false, length = 20)
    private String reference;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "BOOKING_TIME", nullable = false)
    private LocalDateTime bookingTime;

    @Column(name = "TOTAL_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;
}
