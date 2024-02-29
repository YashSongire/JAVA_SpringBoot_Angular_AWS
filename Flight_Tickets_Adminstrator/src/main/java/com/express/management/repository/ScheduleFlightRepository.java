package com.express.management.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.express.management.entity.ScheduledFlight;

@Repository
public interface ScheduleFlightRepository extends JpaRepository<ScheduledFlight, Long>{


	@Query("SELECT sf FROM ScheduledFlight sf " +
	           "JOIN sf.schedule s ON sf.schedule.scheduleid = s.scheduleid " +
	           "JOIN s.source source_airport ON s.source.airportid = source_airport.airportid " +
	           "JOIN s.destination destination_airport ON s.destination.airportid = destination_airport.airportid " +
	           "WHERE source_airport.airportlocation = :sourceLocation " +
	           "AND destination_airport.airportlocation = :destinationLocation " +
	           "AND TO_CHAR(s.dateAndTimeOfDeparture, 'YYYY-MM-DD') = TO_CHAR(:date, 'YYYY-MM-DD')")
	    Optional<List<ScheduledFlight>> findFlightsBySourceAndDestinationAndDeparture(
	            @Param("sourceLocation") String sourceLocation,
	            @Param("destinationLocation") String destinationLocation,
	            @Param("date") Date date);
}


