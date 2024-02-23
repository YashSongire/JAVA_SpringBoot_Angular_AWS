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

import com.express.management.entity.Schedule;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.ImplScheduleService;

import jakarta.validation.Valid;

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
	
}
