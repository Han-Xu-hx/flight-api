/**
 * Flight Booking Controller
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class BookingController {

    public BookingController() {
    }

    @RequestMapping("/common/sample")
    public String sample() {
        return "{\"message\": \"The data from Spring Boot!\"}";
    }

    @RequestMapping("/booking")
    public String booking() {
        return "{\"message\": \"This is the flight booking API!\"}";
    }
}
