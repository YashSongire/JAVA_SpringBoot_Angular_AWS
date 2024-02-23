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

import com.express.management.entity.Passenger;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.ImplPassengerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PassengerController {

	@Autowired
	private ImplPassengerService passservice;
	
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);
	
	// Create Passenger
	@PostMapping("/passengers")
	public ResponseEntity<List<Passenger>> addPassenger(@Valid @RequestBody List<Passenger> passenger){
		LOG.info("Controller - Create Passenger");
		List<Passenger> passdata = passservice.addPassenger(passenger);
		return new ResponseEntity<List<Passenger>>(passdata,HttpStatus.OK);
	}
	
	// All Passengers
	@GetMapping("/passengers")
	public ResponseEntity<Set<Passenger>> viewAllPassenger(){
		LOG.info("Controller - All Schedules");
		Set<Passenger> passdata = passservice.viewAllPassenger();
		return new ResponseEntity<Set<Passenger>>(passdata,HttpStatus.OK);
	}
	
	// Deletion of Passenger
	@DeleteMapping("/passengers/{passengerid}")
	public ResponseEntity<String> removePassenger(@Valid @PathVariable long passengerid) throws ResourceNotFoundException{
		LOG.info("Controller - Delete Data of ID : " + passengerid);
		String message = passservice.removePassenger(passengerid);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	// Update Passengers
	@PutMapping("/passengers")
	public ResponseEntity<List<Passenger>> updatePassenger(@Valid @RequestBody List<Passenger> passenger) throws ResourceNotFoundException{
		LOG.info("Controller - Update Details");
		List<Passenger> updata = passservice.updatePassenger(passenger);
		return new ResponseEntity<List<Passenger>>(updata,HttpStatus.OK);
	}
	
	// Find Passenger By ID
	@GetMapping("/passengers/{passengerid}")
	public ResponseEntity<Passenger> viewPassenger(@Valid @PathVariable long passengerid) throws ResourceNotFoundException{
		LOG.info("Controller - Get Details of ID " + passengerid);
		Passenger pass = passservice.viewPassenger(passengerid);
		return new ResponseEntity<Passenger>(pass,HttpStatus.OK);
	}
}