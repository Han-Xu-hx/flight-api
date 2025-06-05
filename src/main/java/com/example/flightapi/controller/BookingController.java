/**
 * Flight Booking Controller
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.common.BaseResponse;
import com.example.flightapi.dto.FlightBookingRequest;
import com.example.flightapi.dto.MyBookingsRequest;
import com.example.flightapi.services.BookingService;

/**
 * Flight Booking Controller
 */
@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Booking Flight
     * @param flightBookingRequest
     * @return
     */
    @RequestMapping("/book/booking")
    public ResponseEntity<?> booking(@RequestBody FlightBookingRequest flightBookingRequest) {
        
        Map.Entry<Boolean, String> result = bookingService.createBooking(flightBookingRequest);
        
        if (result.getKey()) {
            System.out.println("Booking created successfully");
            return ResponseEntity.ok()
                .body(BaseResponse.success());
        } else {
            System.out.println("Booking creation failed");
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(
                            HttpStatus.BAD_REQUEST.value(),
                            result.getValue(),
                            null));
        }
    }

    /**
     * Obtain My Bookings
     * @param param
     * @return
     */
    @RequestMapping("/book/my-bookings")
    public ResponseEntity<?> obtainMyBookings(@RequestBody MyBookingsRequest param) {
        return ResponseEntity.ok().body(
            BaseResponse.success(Map.of("bookings", bookingService.getMyBookings(param)))
        );
    }
    
}
