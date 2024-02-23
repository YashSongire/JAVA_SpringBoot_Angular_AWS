package com.express.management.services;

import java.util.List;
import java.util.Set;

import com.express.management.entity.Flight;
import com.express.management.exception.ResourceNotFoundException;

public interface ImplFlightService {

	public List<Flight> addFlight(List<Flight> flight);
	public Set<Flight> viewAllFlights();
	public String removeFlight(long flightno) throws ResourceNotFoundException;
	public List<Flight> updateFlight(List<Flight> flight) throws ResourceNotFoundException;
	public Flight viewFlight(long flightid) throws ResourceNotFoundException;
	
}
