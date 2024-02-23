package com.express.management.services;

import java.util.List;
import java.util.Set;

import com.express.management.entity.Bookings;
import com.express.management.exception.ResourceNotFoundException;

public interface ImplBookingService {

	public List<Bookings> addbookings(List<Bookings> bookings);
	public Set<Bookings> viewAllBookings();
	public String removeBookings(long bookingid) throws ResourceNotFoundException;
	public List<Bookings> updateBookings(List<Bookings> bookings) throws ResourceNotFoundException;
	public Bookings viewBookings(long bookingsid) throws ResourceNotFoundException;
}
