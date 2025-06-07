/**
 * Flight Repository
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.flightapi.entity.FlightEntity;

/**
 * Flight Repository
 */
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    @Query(value = """
            select
                f.flight_id as flightId,
                f.airline_corp as airlineCorp,
                f.flight_number as flightNumber,
                c.type_name as typeName,
                f.remaining,
                a1.code as departureAirportCode,
                a1.name as departureAirportName,
                a1.city as departureAirportCity,
                a2.code as destinationAirportCode,
                a2.name as destinationAirportName,
                a2.city as destinationAirportCity,
                f.departure_date as departureDate,
                f.departure_time as departureTime,
                f.arrival_date as arrivalDate,
                f.arrival_time as arrivalTime,
                f.price as price
            from flights f
            inner join master_class_type c on f.class_type_id = c.type_id
            inner join airports a1 on f.departure_airport_id = a1.airport_id
            inner join airports a2 on f.destination_airport_id = a2.airport_id
            where a1.city = :departure and a2.city = :arrival and f.departure_date = :date and
                c.type_name = :cabinClass and
                f.remaining >= :passengers and (f.departure_date > CURRENT_DATE or 
                    (f.departure_date = CURRENT_DATE and f.departure_time > CURRENT_TIME))
            """, nativeQuery = true)
    List<Object[]> findFlights(
        @Param("departure") String departure, 
        @Param("arrival") String arrival, 
        @Param("date") LocalDate date,
        @Param("cabinClass") String cabinClass,
        @Param("passengers") Long passengers);
}