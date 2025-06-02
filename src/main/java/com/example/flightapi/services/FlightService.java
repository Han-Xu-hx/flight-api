package com.example.flightapi.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.flightapi.dto.FlightSearchView;
import com.example.flightapi.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<FlightSearchView> findFlights(String departureAirportCode, String destinationAirportCode, LocalDate departureDate, String cabinClass, Long passengers) {
        List<Object[]> flights = flightRepository.findFlights(departureAirportCode, destinationAirportCode, departureDate, cabinClass, passengers);
        List<FlightSearchView> flightSearchViews = new ArrayList<>();
        for (Object[] flight : flights) {
            FlightSearchView flightSearchView = new FlightSearchView();
            flightSearchView.setFlightId(flight[0] != null? (Long) flight[0] : null);
            flightSearchView.setAirlineCorp(flight[1] != null? (String) flight[1] : null);
            flightSearchView.setFlightNumber(flight[2] != null? (String) flight[2] : null);
            flightSearchView.setTypeName(flight[3] != null? (String) flight[3] : null);
            flightSearchView.setRemaining(flight[4] != null? (Long) flight[4] : null);
            flightSearchView.setDepartureAirportCode(flight[5] != null? (String) flight[5] : null);
            flightSearchView.setDepartureAirportName(flight[6] != null? (String) flight[6] : null);
            flightSearchView.setDepartureAirportCity(flight[7] != null? (String) flight[7] : null);
            flightSearchView.setDestinationAirportCode(flight[8] != null? (String) flight[8] : null);
            flightSearchView.setDestinationAirportName(flight[9] != null? (String) flight[9] : null);
            flightSearchView.setDestinationAirportCity(flight[10] != null? (String) flight[10] : null);
            flightSearchView.setDepartureDate(flight[11] != null ? ((Date) flight[11]).toLocalDate() : null);
            flightSearchView.setDepartureTime(flight[12] != null ? ((Time) flight[12]).toLocalTime() : null);
            flightSearchView.setArrivalDate(flight[13] != null ? ((Date) flight[13]).toLocalDate() : null);
            flightSearchView.setArrivalTime(flight[14] != null ? ((Time) flight[14]).toLocalTime() : null);
            flightSearchView.setPrice(flight[15] != null ? (BigDecimal) flight[15] : null);
            flightSearchViews.add(flightSearchView);
        }
        return flightSearchViews;
    }
}
