package com.pluralsight.spring_data_overview;

import com.pluralsight.spring_data_overview.entity.Flight;
import com.pluralsight.spring_data_overview.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DerivedQueryTests {
  @Autowired private FlightRepository flightRepository;

  @BeforeEach
  public void setUp() {
    flightRepository.deleteAll();
  }

  @Test
  public void shouldFindFlightsFromLondon() {
    final Flight fligh1 = createFlight("London");
    final Flight fligh2 = createFlight("London");
    final Flight fligh3 = createFlight("New York");

    flightRepository.save(fligh1);
    flightRepository.save(fligh2);
    flightRepository.save(fligh3);

    List<Flight> flightsToLondon = flightRepository.findByOrigin("London");

    assertThat(flightsToLondon).hasSize(2);
    assertThat(flightsToLondon.get(0)).isEqualToComparingFieldByField(fligh1);
    assertThat(flightsToLondon.get(1)).isEqualToComparingFieldByField(fligh2);
  }

  @Test
  public void shouldFindFlightsFromLondonToParis() {
    final Flight flight1 = createFlight("London", "Paris");
    final Flight flight2 = createFlight("London", "New York");
    final Flight flight3 = createFlight("Madrid", "Paris");

    flightRepository.save(flight1);
    flightRepository.save(flight2);
    flightRepository.save(flight3);

    List<Flight> flightsToLondonToParis =
        flightRepository.findByOriginAndDestination("London", "Paris");

    assertThat(flightsToLondonToParis).hasSize(1).first().isEqualToComparingFieldByField(flight1);
  }

  private Flight createFlight(String origin, String destination) {
    final Flight flight = new Flight();
    flight.setOrigin(origin);
    flight.setDestination(destination);
    flight.setScheduledAt(LocalDateTime.parse("2023-01-01T12:00:00"));
    return flight;
  }

  private Flight createFlight(String origin) {
    return createFlight(origin, "Madrid");
  }
}
