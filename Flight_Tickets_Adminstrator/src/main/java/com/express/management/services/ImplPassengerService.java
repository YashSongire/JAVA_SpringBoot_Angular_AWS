package com.express.management.services;

import java.util.List;
import java.util.Set;

import com.express.management.entity.Passenger;
import com.express.management.exception.ResourceNotFoundException;

public interface ImplPassengerService {

	public List<Passenger> addPassenger(List<Passenger> schedule);
	public Set<Passenger> viewAllPassenger();
	public String removePassenger(long passengerid) throws ResourceNotFoundException;
	public List<Passenger> updatePassenger(List<Passenger> passenger) throws ResourceNotFoundException;
	public Passenger viewPassenger(long passengerid) throws ResourceNotFoundException;
	
}
