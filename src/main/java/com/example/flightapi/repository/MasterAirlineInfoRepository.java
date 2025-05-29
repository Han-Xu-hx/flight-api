/**
 * Airline Information Repository
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightapi.entity.MasterAirlineInfoEntity;

/**
 * Airline Information Repository
 */
public interface MasterAirlineInfoRepository extends JpaRepository<MasterAirlineInfoEntity, Long> {

}
