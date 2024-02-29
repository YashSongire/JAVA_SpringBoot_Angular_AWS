package com.express.management.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.express.management.entity.Schedule;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.ImplScheduleService;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ScheduleController { 
	
	@Autowired
	private ImplScheduleService schservice;
	
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);
	
	// Create Schedule
	@PostMapping("/schedules")
	public ResponseEntity<List<Schedule>> addSchedules(@Valid @RequestBody List<Schedule> schedule){
		LOG.info("Controller - Create Schedules");
		List<Schedule> schdata = schservice.addSchedules(schedule);
		return new ResponseEntity<List<Schedule>>(schdata,HttpStatus.OK);
	}
	
	// All Schedules
	@GetMapping("/schedules")
	public ResponseEntity<Set<Schedule>> viewAllSchedule(){
		LOG.info("Controller - All Schedules");
		Set<Schedule> schdata = schservice.viewAllSchedule();
		return new ResponseEntity<Set<Schedule>>(schdata,HttpStatus.OK);
	}
	
	// Deletion of Schedule
	@DeleteMapping("/schedules/{scheduleid}")
	public ResponseEntity<String> removeSchedule(@Valid @PathVariable long scheduleid) throws ResourceNotFoundException{
		LOG.info("Controller - Delete Data of ID : " +scheduleid);
		String message = schservice.removeSchedule(scheduleid);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	// Update Schedules
	@PutMapping("/schedules")
	public ResponseEntity<List<Schedule>> updateSchedule(@Valid @RequestBody List<Schedule> schedule) throws ResourceNotFoundException{
		LOG.info("Controller - Update Details");
		List<Schedule> updata = schservice.updateSchedule(schedule);
		return new ResponseEntity<List<Schedule>>(updata,HttpStatus.OK);
	}
	
	// Find Schedule By ID
	@GetMapping("/schedules/{scheduleid}")
	public ResponseEntity<Schedule> viewSchedule(@Valid @PathVariable long scheduleid) throws ResourceNotFoundException{
		LOG.info("Controller - Get Details of ID " + scheduleid);
		Schedule sch = schservice.viewSchedule(scheduleid);
		return new ResponseEntity<Schedule>(sch,HttpStatus.OK);
	}
	
	// Find Schedule By Date & Time
	@GetMapping("/schedules/departuredateandtime/{departures}")
	public ResponseEntity<List<Schedule>> viewAllbyDatetime(@Valid @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date departures) throws ResourceNotFoundException, ParseException{
		LOG.info("Controller - Get Details of ID " + departures);
		List<Schedule> sch = schservice.findAllWithCreationDateTime(departures);
		return new ResponseEntity<List<Schedule>>(sch,HttpStatus.OK);
	}
	
	// Find Schedule By Date
	@GetMapping("/schedules/departuredate/{departures}")
	public ResponseEntity<List<Schedule>> viewAllbyDate(@PathVariable("departures") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departures) throws ResourceNotFoundException, ParseException{
		LOG.info("Controller - Get Details of Date " + departures);
		List<Schedule> sch = schservice.findAllWithdate(departures);
		return new ResponseEntity<List<Schedule>>(sch,HttpStatus.OK);
	}
}
