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

import com.express.management.entity.ScheduledFlight;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.ImplScheduleFlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ScheduledFlightController {
	
	@Autowired
	private ImplScheduleFlightService schflservice;
	
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);
	
	// Create Schedule Flight
	@PostMapping("/scheduleflight")
	public ResponseEntity<List<ScheduledFlight>>  addScheduledFlight(@Valid @RequestBody List<ScheduledFlight> scheduledflight){
		LOG.info("Controller - Add ScheduledFlight");
		List<ScheduledFlight> schflightdata = schflservice.addScheduledFlight(scheduledflight);
		return new ResponseEntity<List<ScheduledFlight>>(schflightdata,HttpStatus.OK);
	}
	
	//Get All Scheduled Flights 
	@GetMapping("/scheduleflight")
	public ResponseEntity<Set<ScheduledFlight>> viewAllScheduledFlight(){
		LOG.info("Controller - ALL ScheduledFlight");
		Set<ScheduledFlight> schflightdata = schflservice.viewAllScheduledFlight();
		return new ResponseEntity<Set<ScheduledFlight>>(schflightdata,HttpStatus.OK);
	}
	
	// Deletion of ScheduleFlight
	@DeleteMapping("/scheduleflight/{scheduledflightid}")
	public ResponseEntity<String> removeScheduledFlight(@Valid @PathVariable long scheduledflightid) throws ResourceNotFoundException{
		LOG.info("Controller - Delete ScheduledFlight");
		String msg = schflservice.removeScheduledFlight(scheduledflightid);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	};
	
	// Update ScheduledFlight
	@PutMapping("/scheduleflight")
	public ResponseEntity<List<ScheduledFlight>> updateScheduledFlight(@Valid @RequestBody  List<ScheduledFlight> scheduledflight) throws ResourceNotFoundException{
		LOG.info("Controller - Update ScheduledFlight");
		List<ScheduledFlight> schflightdata = schflservice.updateScheduledFlight(scheduledflight);
		return new ResponseEntity<List<ScheduledFlight>>(schflightdata,HttpStatus.OK);
	}
	
	//Find By ID
	@GetMapping("/scheduleflight/{scheduledflightid}")
	public ResponseEntity<ScheduledFlight> viewScheduledFlight(@Valid @PathVariable long scheduledflightid) throws ResourceNotFoundException{
		LOG.info("Controller - Find ScheduledFlight By ID");
		ScheduledFlight scheduleflight = schflservice.viewScheduledFlight(scheduledflightid);
		return new ResponseEntity<ScheduledFlight>(scheduleflight,HttpStatus.OK);
	}
}
