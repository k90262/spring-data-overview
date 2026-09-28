package com.pluralsight.spring_data_overview;

import com.pluralsight.spring_data_overview.entity.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class SpringDataOverviewApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Test
	void verifyFlightCanBeSaved() {
		final Flight flight = new Flight();
		flight.setOrigin("SFO");
		flight.setDestination("LAX");
		flight.setScheduledAt(LocalDateTime.parse("2023-09-01T12:00:00"));

		entityManager.persist(flight);

		final TypedQuery<Flight> query = entityManager
				.createQuery("SELECT f FROM Flight f", Flight.class);
		final List<Flight> resultList = query.getResultList();
		Assertions.assertThat(resultList)
				.hasSize(1).first().isEqualTo(flight);
	}

}
