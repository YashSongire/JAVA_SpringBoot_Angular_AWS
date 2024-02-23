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
import com.express.management.entity.Flight;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.ImplFlightService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class FlightController {

	@Autowired
	private ImplFlightService Flservice;
	
	// Creating Logger Object
		public static final Logger LOG = LoggerFactory.getLogger(FlightController.class);
		
	// Adding flight Details
		@PostMapping("/flights")
		public ResponseEntity<List<Flight>> addFlight(@Valid @RequestBody List<Flight> flight){
			LOG.info("RestConttroller - Creating Flight");
			List<Flight> flightData= Flservice.addFlight(flight);
			return new ResponseEntity<List<Flight>>(flightData,HttpStatus.OK);	
		}
		
	// Get All Flights Details 
		@GetMapping("/flights")
		public ResponseEntity<Set<Flight>> viewAllFlights(){
			LOG.info("RestConttroller - Fetch All Flights Details");
			Set<Flight> flight = Flservice.viewAllFlights();
			return new ResponseEntity<Set<Flight>>(flight,HttpStatus.OK);
		}
	
		
	// Get Flight Details By ID
		@GetMapping("/flights/{flightid}")
		public ResponseEntity<Flight> viewFlight(@PathVariable long flightid) throws ResourceNotFoundException{
			LOG.info("RestConttroller - Fetch Flight ID - "+ flightid);
			Flight flight = Flservice.viewFlight(flightid);
			return new ResponseEntity<Flight>(flight,HttpStatus.OK);
		}
		
	//Remove Existing Flight Details
		@DeleteMapping("/flights/{flightid}")
		public ResponseEntity<String> removeFlight(@Valid @PathVariable long flightid) throws ResourceNotFoundException{
			LOG.info("RestConttroller - Delete Flight ID - " + flightid);
			String message = Flservice.removeFlight(flightid);
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		
	// Update the Existing Flight
		@PutMapping("/flights")
		public ResponseEntity<List<Flight>> updateflight(@Valid @RequestBody List<Flight> flight) throws ResourceNotFoundException{
			LOG.info("RestConttroller - updateflight");
			List<Flight> upflight = Flservice.updateFlight(flight);
			return new ResponseEntity<List<Flight>>(upflight,HttpStatus.OK);
		}
}
