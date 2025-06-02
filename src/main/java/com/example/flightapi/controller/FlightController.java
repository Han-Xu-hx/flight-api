package com.example.flightapi.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.common.BaseResponse;
import com.example.flightapi.dto.FlightSearchRequest;
import com.example.flightapi.dto.FlightSearchResponse;
import com.example.flightapi.dto.FlightSearchView;
import com.example.flightapi.services.FlightService;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping("/common/flights-search")
    public ResponseEntity<?> searchFlights(@RequestBody FlightSearchRequest request) {
        
        List<FlightSearchResponse> flights = new ArrayList<FlightSearchResponse>();
        List<FlightSearchResponse> returnFlights = new ArrayList<FlightSearchResponse>();

        List<FlightSearchView> flightList = null;
        flightList = flightService.findFlights(request.getDeparture(), request.getArrival(), 
                request.getDate(), request.getCabinClass(), request.getPassengers());


        Function<FlightSearchView, String> calcDuration = flight -> {
            LocalDateTime departureDateTime = LocalDateTime.of(flight.getDepartureDate(), flight.getDepartureTime());
            LocalDateTime arrivalDateTime = LocalDateTime.of(flight.getArrivalDate(), flight.getArrivalTime());
            Duration duration = Duration.between(departureDateTime, arrivalDateTime);
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            return (hours + "h " + minutes + "m");
        };
        if (flightList != null && !flightList.isEmpty()) {
            flightList.forEach(flight -> {
                FlightSearchResponse flightResponse = new FlightSearchResponse();
                flightResponse.setId(flight.getFlightId());
                flightResponse.setAirline(flight.getAirlineCorp());
                flightResponse.setFlightNo(flight.getFlightNumber());
                flightResponse.setDeparture(flight.getDepartureAirportCity());
                flightResponse.setArrival(flight.getDestinationAirportCity());
                flightResponse.setDate(flight.getDepartureDate());
                flightResponse.setTime(flight.getDepartureTime());
                flightResponse.setArrivalDate(flight.getArrivalDate());
                flightResponse.setArrivalTime(flight.getArrivalTime());
                flightResponse.setDuration(calcDuration.apply(flight));
                flightResponse.setPrice(flight.getPrice());
                flightResponse.setRemainingSeats(flight.getRemaining());
                flightResponse.setCabinClass(flight.getTypeName());
                flights.add(flightResponse);
            });
        }
        
        if ("round-trip".equals(request.getTripType())) {
            List<FlightSearchView> roundTripList = null;
            roundTripList = flightService.findFlights(request.getArrival(), request.getDeparture(), 
                    request.getReturnDate(), request.getCabinClass(), request.getPassengers());
            if (roundTripList != null && !roundTripList.isEmpty()) {
                roundTripList.forEach(flight -> {
                    FlightSearchResponse flightResponse = new FlightSearchResponse();
                    flightResponse.setId(flight.getFlightId());
                    flightResponse.setAirline(flight.getAirlineCorp());
                    flightResponse.setFlightNo(flight.getFlightNumber());
                    flightResponse.setDeparture(flight.getDepartureAirportCity());
                    flightResponse.setArrival(flight.getDestinationAirportCity());
                    flightResponse.setDate(flight.getArrivalDate());
                    flightResponse.setTime(flight.getArrivalTime());
                    flightResponse.setArrivalDate(flight.getDepartureDate());
                    flightResponse.setArrivalTime(flight.getDepartureTime());
                    flightResponse.setDuration(calcDuration.apply(flight));
                    flightResponse.setPrice(flight.getPrice());
                    flightResponse.setRemainingSeats(flight.getRemaining());
                    flightResponse.setCabinClass(flight.getTypeName());
                    returnFlights.add(flightResponse);
                });
            }
        }

        return ResponseEntity.ok()
               .body(BaseResponse.success(
                  Map.of(
                    "flights", flights,
                    "returnFlights", returnFlights)
               ));
    }
}
