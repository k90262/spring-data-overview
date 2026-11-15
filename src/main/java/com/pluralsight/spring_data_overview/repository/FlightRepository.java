package com.pluralsight.spring_data_overview.repository;

import com.pluralsight.spring_data_overview.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Long> {

    // Equals: SELECT * FROM flight WHERE origin = ?
    List<Flight> findByOrigin(String origin);

    // Equals: SELECT * FROM flight WHERE origin = ? AND destination = ?
    List<Flight> findByOriginAndDestination(String origin, String destination);

    // Equals: SELECT * FROM flight WHERE origin IN (?)
    List<Flight> findByOriginIn(String ... origins);

    // Equals: SELECT * FROM flight WHERE upper(origin) = upper(?)
    List<Flight> findByOriginIgnoreCase(String origin);
}
