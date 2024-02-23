package com.express.management.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.express.management.entity.Bookings;
import com.express.management.entity.Passenger;
import com.express.management.entity.ScheduledFlight;
import com.express.management.entity.User;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.BookingsRepository;
import com.express.management.repository.PassengerRepository;
import com.express.management.repository.ScheduleFlightRepository;
import com.express.management.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingService implements ImplBookingService{

	@Autowired
	private BookingsRepository bookrepo;
	@Autowired
	private ScheduleFlightRepository schflrepo;
	@Autowired
	private UserRepository userepo;
	@Autowired
	private PassengerRepository passrepo;
	
	public static final Logger LOG = LoggerFactory.getLogger(BookingService.class);
	
	@Override
	public List<Bookings> addbookings(List<Bookings> bookings) {
		// TODO Auto-generated method stub
		LOG.info("Service - Booking Add");
		List<Bookings> newbooking = new ArrayList<>();
	    for (Bookings book : bookings) {
	        Optional<ScheduledFlight> schedulefl = schflrepo.findById(book.getScheduledflight().getScheduleflightid());
	        Optional<User> user = userepo.findById(book.getUser().getUserid());
	        List<Passenger> listpass = new ArrayList<>();
	        	if (schedulefl.isPresent() || user.isPresent()){
	        		for(Passenger pass : book.getPassengers()) {
	        			Optional<Passenger> findpass = passrepo.findById(pass.getPassengerid());
	        			if(findpass.isPresent()) {
	        				listpass.add(findpass.get());
	        		}
	        			else {
	        				passrepo.save(pass);
	        				listpass.add(pass);
	        			}
	        		}
	        		Bookings booknew = new Bookings(user.get(),book.getBookingdate(),listpass,book.getBookingcost(),schedulefl.get());
	        		newbooking.add(booknew);
	        		bookrepo.saveAll(newbooking);
	        		}
	        	else {
	        		throw new ResourceNotFoundException("Wrong ScheduleFlight IDs: " + book.getScheduledflight().getScheduleflightid() + 
	        				" or Wrong User ID " + book.getUser().getUserid());
	        		}
	        	}
		return newbooking;
	}

	@Override
	public Set<Bookings> viewAllBookings() {
		// TODO Auto-generated method stub
		LOG.info("Service - All Bookings");
		Set<Bookings> book = new HashSet<>();
		bookrepo.findAll().forEach(book1 -> book.add(book1));
		return book;
	}

	@Override
	public String removeBookings(long bookingid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service - Booking Delete");
		Optional<Bookings> deletebooking = bookrepo.findById(bookingid);
		if(deletebooking.isPresent()) {
			bookrepo.deleteById(bookingid);
		}
		else
			throw new ResourceNotFoundException("Booking Doesn't Exist for ID : " + bookingid);
		
		return "Booking with ID " + bookingid + " Deleted"; 
	}

	@Override
	public List<Bookings> updateBookings(List<Bookings> bookings) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service - Booking");
		List<Bookings> upbooking = new ArrayList<>();
	    for (Bookings book : bookings) {
	    	Optional<Bookings> checkbooking = bookrepo.findById(book.getBookingid());
	    	if(checkbooking.isPresent()) {
	    		Optional<ScheduledFlight> uschfl = schflrepo.findById(book.getScheduledflight().getScheduleflightid());
		    	 Optional<User> user = userepo.findById(book.getUser().getUserid());
		        	if (uschfl.isPresent() || user.isPresent()){
		        		Bookings booknew = new Bookings(book.getBookingid(),user.get(),book.getBookingdate(),book.getPassengers(),book.getBookingcost(),book.getScheduledflight());
		        		upbooking.add(booknew);
		        		bookrepo.saveAll(upbooking);
		        		}
		        	else {
		        		throw new ResourceNotFoundException("Wrong ScheduleFlight IDs: " + book.getScheduledflight().getScheduleflightid() + 
		        				" or Wrong User ID " + book.getUser().getUserid());
		        		}
		        	}
	    	else
	    		throw new ResourceNotFoundException("Wrong Booking ID :" + book.getBookingid());
        		}
	    return upbooking;
	    	}

	@Override
	public Bookings viewBookings(long bookingsid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service - Booking");
		Optional<Bookings> findbooking = bookrepo.findById(bookingsid);
		if(findbooking.isPresent()) {
			return findbooking.get();
		}
		else
			throw new ResourceNotFoundException("Booking Doesn't Exist for ID : " + bookingsid);
	}
}
