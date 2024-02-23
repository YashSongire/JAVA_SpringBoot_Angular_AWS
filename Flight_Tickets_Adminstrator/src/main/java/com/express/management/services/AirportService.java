package com.express.management.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.express.management.entity.Airport;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.AirportRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AirportService implements ImplAirportService{

	@Autowired
	private AirportRepository airRepo;
	
	public static final Logger LOG = LoggerFactory.getLogger(FlightService.class);
	
	// Creation of Airports Data 
	@Override
	public List<Airport> addAirport(List<Airport> airport) {
		// TODO Auto-generated method stub
		LOG.info("Service - Airport Creation");
		List<Airport> AirportData=airRepo.saveAll(airport);
		return AirportData;
	}

	// All Airports Data
	@Override
	public Set<Airport> viewAllAirports() {
		// TODO Auto-generated method stub
		LOG.info("Service - All Airports");
		Set<Airport> Airportdata = new HashSet<Airport>();
		airRepo.findAll().forEach(Airport1 -> Airportdata.add(Airport1));
		return Airportdata; 
	}

	// Deletion of Airport
	@Override
	public String removeAirport(long airportid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service -  Remove Airport" + airportid);
		Optional<Airport> optionalAirport = airRepo.findById(airportid);
		if(optionalAirport.isPresent()) {
			airRepo.deleteById(airportid);
		}
		else
			throw new ResourceNotFoundException("Aiport Doesn't Exist for ID : " + airportid);
		
		return "Airport with ID " + airportid + " Deleted"; 
	}
	
	// Update Airport Details
	@Override
	public List<Airport> updateAirport(List<Airport> airports) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service -  Airport Update");
		for(Airport updateAir : airports) {
			Optional<Airport> existingAirport = airRepo.findById(updateAir.getAirportid());
			if(existingAirport.isPresent()) {
				Airport UpdatedAirport = existingAirport.get();
				UpdatedAirport.setAirportname(updateAir.getAirportname());
				UpdatedAirport.setAirportcode(updateAir.getAirportcode());
				UpdatedAirport.setAirportlocation(updateAir.getAirportlocation());
			airRepo.save(UpdatedAirport);
			}
 		}
		return airports;
	}

	// Get Airports Details BY ID
	@Override
	public Airport viewAirport(long airportid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service - Airport Details - " +airportid);		
		Optional<Airport> airport = airRepo.findById(airportid);
		if(airport.isPresent()) {
			return airport.get();
		}
		else 
			throw new ResourceNotFoundException("No Airport Associated with : " +airportid);
	}
}
