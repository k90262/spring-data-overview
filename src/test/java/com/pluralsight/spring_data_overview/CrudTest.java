package com.pluralsight.spring_data_overview;

import com.pluralsight.spring_data_overview.entity.Flight;
import com.pluralsight.spring_data_overview.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CrudTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void shouldPerformCrudOperations() {
        final Flight flight = new Flight();
        flight.setOrigin("London");
        flight.setDestination("New York");
        flight.setScheduledAt(LocalDateTime.parse("2023-01-01T12:00:00"));

        flightRepository.save(flight);

        assertThat(flightRepository.findAll())
                .hasSize(1)
                .first()
                .isEqualToComparingFieldByField(flight);

        flightRepository.deleteById(flight.getId());

        assertThat(flightRepository.count()).isZero();
    }
}
