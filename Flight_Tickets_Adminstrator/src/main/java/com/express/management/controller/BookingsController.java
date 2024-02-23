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

import com.express.management.entity.Bookings;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.BookingService;
import com.express.management.services.ImplBookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BookingsController {

	@Autowired
	private ImplBookingService bookservice;
	
	public static final Logger LOG = LoggerFactory.getLogger(BookingService.class);
	
	// Create Bookings
	@PostMapping("/bookings")
	public ResponseEntity<List<Bookings>>  addBookings(@Valid @RequestBody List<Bookings> bookings){
		LOG.info("Controller - Add Bookings");
		List<Bookings> book = bookservice.addbookings(bookings);
		return new ResponseEntity<List<Bookings>>(book,HttpStatus.OK);
	}
	
	//Get All Bookings
	@GetMapping("/bookings")
	public ResponseEntity<Set<Bookings>> viewAllBookings(){
		LOG.info("Controller - ALL ScheduledFlight");
		Set<Bookings> book = bookservice.viewAllBookings();
		return new ResponseEntity<Set<Bookings>>(book,HttpStatus.OK);
	}
	
	// Deletion of Bookings
	@DeleteMapping("/bookings/{bookingid}")
	public ResponseEntity<String> removebookings(@Valid @PathVariable long bookingid) throws ResourceNotFoundException{
		LOG.info("Controller - Delete Bookings");
		String msg = bookservice.removeBookings(bookingid);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	};
	
	// Update Bookings
	@PutMapping("/bookings")
	public ResponseEntity<List<Bookings>> updateBookings(@Valid @RequestBody  List<Bookings> bookings) throws ResourceNotFoundException{
		LOG.info("Controller - Update Bookings");
		List<Bookings> book = bookservice.updateBookings(bookings);
		return new ResponseEntity<List<Bookings>>(book,HttpStatus.OK);
	}
	
	//Find By Bookings ID
	@GetMapping("/bookings/{bookingid}")
	public ResponseEntity<Bookings> viewBookings(@Valid @PathVariable long bookingid) throws ResourceNotFoundException{
		LOG.info("Controller - Find Bookings By ID");
		Bookings book = bookservice.viewBookings(bookingid);
		return new ResponseEntity<Bookings>(book,HttpStatus.OK);
	}
}
