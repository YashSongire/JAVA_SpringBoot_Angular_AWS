package com.express.management.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.express.management.entity.Flight;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.FlightRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlightService implements ImplFlightService{
	
	@Autowired
	private FlightRepository flrepository;
	
	// creating logger object
	public static final Logger LOG = LoggerFactory.getLogger(FlightService.class);
	
	// Creation of Flight
	@Override
	public List<Flight> addFlight(List<Flight> flight) {
		LOG.info("Service - Flight Creation");
		List<Flight> flightData=flrepository.saveAll(flight);
		return flightData;
	}
	
	// Get Flight Details By ID
	@Override
	public Flight viewFlight(long flightid) throws ResourceNotFoundException{
		LOG.info("Service - Flight Details of ID " + flightid);
		Optional<Flight> flight = flrepository.findById(flightid);
		if(flight.isPresent())
		{
			return flight.get();
		}
		else {
			throw new ResourceNotFoundException("No Flight For This ID");
		}
	}
	
	// Get All Flight Details
	@Override
	public Set<Flight> viewAllFlights(){
		LOG.info("Service - All Flights");
		Set<Flight> flightdata = new HashSet<Flight>();
		flrepository.findAll().forEach(flight1 -> flightdata.add(flight1));
		return flightdata; 
	}
	
	
	//Remove Existing Flight Details
	@Override
	public String removeFlight(long flightid) throws ResourceNotFoundException {
		LOG.info("Service - Flight Deletion : " + flightid);
		Optional<Flight> existingFlightOptional = flrepository.findById(flightid);
        if (existingFlightOptional.isPresent()) {
            // If the flight exists, delete it
        	flrepository.deleteById(flightid);
        } else {
            // Handle the case where the flight with the given ID does not exist
            throw new ResourceNotFoundException("Flight with ID " + flightid + " not found");
        }
		return "Flight with ID " + flightid + " Deleted";
	}
	
	// Update the Existing Flight
	@Override
	public List<Flight> updateFlight(List<Flight> flight) throws ResourceNotFoundException{
		LOG.info("Service - Update Flights");
		for (Flight flupdate : flight) {
			Optional<Flight> existingFlightOptional = flrepository.findById(flupdate.getFlightId());

	        if (existingFlightOptional.isPresent()) {
	            Flight existingFlight = existingFlightOptional.get();
	            existingFlight.setCarrierName(flupdate.getCarrierName());
	            existingFlight.setFlightModel(flupdate.getFlightModel());
	            existingFlight.setSeatCapacity(flupdate.getSeatCapacity());
	            // Save the updated flight back to the database
	            flrepository.save(existingFlight);
	
	        } else {
	            // Handle the case where the flight with the given ID does not exist
	            throw new ResourceNotFoundException("Flight with ID " + flupdate.getFlightId() + " not found");
	        }
		}
		return flight;	
	}
}



