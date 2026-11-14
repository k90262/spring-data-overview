package com.pluralsight.spring_data_overview.repository;

import com.pluralsight.spring_data_overview.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findByOrigin(String origin);

    List<Flight> findByOriginAndDestination(String origin, String destination);
}
