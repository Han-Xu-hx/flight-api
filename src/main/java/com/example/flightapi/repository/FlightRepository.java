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
                f.flight_number,
                c.type_name,
                f.remaining,
                a1.code as departure_airport_code,
                a1.name as departure_airport_name,
                a1.city as departure_airport_city,
                a2.code as destination_airport_code,
                a2.name as destination_airport_name,
                a2.city as destination_airport_city,
                f.departure_date,
                f.departure_time,
                f.arrival_date,
                f.arrival_time
            from flights f
            inner join master_class_type c on f.class_type_id = c.class_type_id
            inner join airports a1 on f.departure_airport_id = a1.airport_id
            inner join airports a2 on f.destination_airport_id = a2.airport_id
            where a1.city = :fromCity and a2.city = :toCity and f.departure_date = :departureDate and
                f.remaining > 0 and (f.departureDate > CURRENT_DATE or 
                    (f.departureDate = CURRENT_DATE and f.departureTime > CURRENT_TIME))
            """, nativeQuery = true)
    List<Object[]> findFlights(
        @Param("fromCity") String fromCity, 
        @Param("toCity") String toCity, 
        @Param("departureDate") LocalDate departureDate);
}