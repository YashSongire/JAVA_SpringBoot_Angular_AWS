package com.express.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.express.management.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>{

	public Optional<Airport> findById(long airportid);

	public Optional<Airport> deleteById(long airportid);
}
