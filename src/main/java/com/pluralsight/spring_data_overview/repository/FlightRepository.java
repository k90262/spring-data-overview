package com.pluralsight.spring_data_overview.repository;

import com.pluralsight.spring_data_overview.entity.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Long> {}
