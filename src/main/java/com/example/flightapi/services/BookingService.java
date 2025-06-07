/**
 * Flight Booking Service
 * Copyright (C) 2025
 * Created by hanxu on 2025/06/05.
 */
package com.example.flightapi.services;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.flightapi.dto.FlightBookingRequest;
import com.example.flightapi.dto.MyBookingResponse;
import com.example.flightapi.dto.MyBookingsRequest;
import com.example.flightapi.dto.PassengerDTO;
import com.example.flightapi.entity.BookingEntity;
import com.example.flightapi.entity.FlightEntity;
import com.example.flightapi.entity.PassengerEntity;
import com.example.flightapi.repository.BookingRepository;
import com.example.flightapi.repository.FlightRepository;
import com.example.flightapi.repository.PassengerRepository;
import com.example.flightapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Flight Booking Service
 */
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final PassengerRepository passengerRepository;

    /**
     * Create a new booking
     * @param request
     * @return
     */
    @Transactional
    public Map.Entry<Boolean, String> createBooking(FlightBookingRequest request) {

        int numOfPassengers = request.getPassengers().size();
        FlightEntity f = flightRepository.findById(request.getBooking().getId()).get();
        long remainingSeats = f.getRemaining() - numOfPassengers;
        if (remainingSeats < 0) {
            return Map.entry(false, "Not enough seats");
        }

        f.setRemaining(remainingSeats);
        flightRepository.save(f);

        BookingEntity booking = new BookingEntity();
        booking.setUser(userRepository.getReferenceById(request.getUserId()));
        booking.setFlight(flightRepository.getReferenceById(request.getBooking().getId()));
        booking.setReference(request.getReference());
        booking.setStatus("UPCOMING");
        
        booking.setTotalPrice(request.getBooking().getPrice().multiply(BigDecimal.valueOf(numOfPassengers)));
        bookingRepository.save(booking);

        List<PassengerEntity> passengers = request.getPassengers().stream().map(passenger -> {
            PassengerEntity p = new PassengerEntity();
            p.setBooking(booking);
            p.setFirstName(passenger.getFirstName());
            p.setLastName(passenger.getLastName());
            p.setEmail(passenger.getEmail());
            p.setReference(booking.getReference());
            p.setBooking(booking);
            return p;
        }).collect(Collectors.toList());
        passengerRepository.saveAll(passengers);
        return Map.entry(true, "Booking created successfully");
    }

    /**
     * Get user bookings
     * @param request
     * @return
     */
    public List<MyBookingResponse> getMyBookings(MyBookingsRequest request) {

        List<MyBookingResponse> responses = new ArrayList<MyBookingResponse>();
        List<BookingEntity> bookings = bookingRepository.findByUser(userRepository.getReferenceById(request.getUserId()));
        
        Function<FlightEntity, String> calcDuration = flight -> {
            LocalDateTime departureDateTime = LocalDateTime.of(flight.getDepartureDate(), flight.getDepartureTime());
            LocalDateTime arrivalDateTime = LocalDateTime.of(flight.getArrivalDate(), flight.getArrivalTime());
            Duration duration = Duration.between(departureDateTime, arrivalDateTime);
            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            return (hours + "h " + minutes + "m");
        };
        
        if (bookings != null && bookings.size() > 0) {
            bookings.forEach(booking -> {
                MyBookingResponse response = new MyBookingResponse();
                response.setBookingId(booking.getBookingId());
                response.setFlightNumber(booking.getFlight().getFlightNumber());
                response.setDepartureDate(booking.getFlight().getDepartureDate());
                response.setDepartureTime(booking.getFlight().getDepartureTime());
                response.setDepartureAirport(booking.getFlight().getDepartureAirport().getName());
                response.setArrivalAirport(booking.getFlight().getDestinationAirport().getName());
                response.setArrivalDate(booking.getFlight().getArrivalDate());
                response.setArrivalTime(booking.getFlight().getArrivalTime());
                response.setDuration(calcDuration.apply(booking.getFlight()));
                response.setStatus(booking.getStatus());
                response.setTotalPrice(booking.getTotalPrice().toString());
                response.setReference(booking.getReference());
                List<PassengerEntity> passengers = booking.getPassengers();
                List<PassengerDTO> passengerDTOs = new ArrayList<PassengerDTO>();
                passengers.forEach(passenger -> {
                    PassengerDTO passengerDTO = new PassengerDTO();
                    passengerDTO.setFirstName(passenger.getFirstName());
                    passengerDTO.setLastName(passenger.getLastName());
                    passengerDTO.setEmail(passenger.getEmail());
                    passengerDTOs.add(passengerDTO);
                });
                response.setPassengers(passengerDTOs);
                responses.add(response);
            });
        }
        return responses;
    }
}
