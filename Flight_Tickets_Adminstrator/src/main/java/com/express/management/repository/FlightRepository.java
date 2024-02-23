package com.express.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.express.management.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

	public Optional<Flight> findById(long flightId);

	public Optional<Flight> deleteById(long flightId);	
}
