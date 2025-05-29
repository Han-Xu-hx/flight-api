/**
 * User Controller
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/27.
 */
package com.example.flightapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.common.BaseResponse;
import com.example.flightapi.entity.AirportEntity;
import com.example.flightapi.repository.AirportRepository;

@RestController
@RequestMapping("/api/common")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @RequestMapping("/airports")
    public ResponseEntity<?> getAirports() {
        List<AirportEntity> airports = airportRepository.findAll();
        return ResponseEntity.ok()
                .body(BaseResponse.success(
                        Map.of(
                                "airports", airports)));
    }
}
