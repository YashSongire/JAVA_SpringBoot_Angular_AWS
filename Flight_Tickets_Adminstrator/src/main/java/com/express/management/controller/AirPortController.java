package com.express.management.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.express.management.entity.Airport;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.ImplAirportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AirPortController {
	
	@Autowired
	private ImplAirportService airservice;
	
	public static final Logger LOG = LoggerFactory.getLogger(AirPortController.class);
	
	// All Airports
	@GetMapping("/airports")
	public ResponseEntity<Set<Airport>> getAirportAll(){
		LOG.info("Controller - All Airports");
		Set<Airport> airportdata = airservice.viewAllAirports();
		return new ResponseEntity<Set<Airport>>(airportdata,HttpStatus.OK);
	}
	
	// Creation of Airports
	@PostMapping("/airports")
	public ResponseEntity<List<Airport>> addAirport(@Valid @RequestBody List<Airport> airport) {
		LOG.info("Controller - All Airports");
		List<Airport> airdata = airservice.addAirport(airport);
		return new ResponseEntity<List<Airport>>(airdata,HttpStatus.OK);
	}
	
	// Deletion of Airport 
	@DeleteMapping("/airports/{airportid}")
	public ResponseEntity<String> removeAirport(@PathVariable long airportid) throws ResourceNotFoundException{
		LOG.info("Controller - Delete Airport - " +airportid);
		String message = airservice.removeAirport(airportid);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	// Update Airport
	@PutMapping("/airports")
	public ResponseEntity<List<Airport>> updateAirport(@Valid @RequestBody List<Airport> airport) throws ResourceNotFoundException {
		LOG.info("Controller - Update Airport");
		List<Airport> airportdata = airservice.updateAirport(airport);
		return new ResponseEntity<List<Airport>>(airportdata,HttpStatus.OK);
	}
	
	// GET Airport by ID
	@GetMapping("/airports/{airportid}")
	public ResponseEntity<Airport> viewAirport(@PathVariable long airportid) throws ResourceNotFoundException{
		LOG.info("Controller - Details of Airport ID - " +airportid);
		Airport airport = airservice.viewAirport(airportid);
		return new ResponseEntity<Airport>(airport,HttpStatus.OK);
	}
}
