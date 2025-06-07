/**
 * Flight Api Application
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.flightapi.util.JwtProperties;
import com.example.flightapi.util.SecurityProperties;

/**
 * Flight Api Application
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {JwtProperties.class, SecurityProperties.class})
public class FlightApiApplication {

	/**
	 * Main Method
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FlightApiApplication.class, args);
	}
}