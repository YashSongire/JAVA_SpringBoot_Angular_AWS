package com.express.management.services;

import java.util.List;
import java.util.Set;

import com.express.management.entity.Airport;
import com.express.management.exception.ResourceNotFoundException;

public interface ImplAirportService {

	public List<Airport> addAirport(List<Airport> airport);
	public Set<Airport> viewAllAirports();
	public String removeAirport(long airportid) throws ResourceNotFoundException;
	public List<Airport> updateAirport(List<Airport> airport) throws ResourceNotFoundException;
	public Airport viewAirport(long airportid) throws ResourceNotFoundException; 
	public Airport findbylocation(String location) throws ResourceNotFoundException;
}
