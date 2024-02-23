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

import com.express.management.entity.Passenger;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.PassengerRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PassengerService implements ImplPassengerService{

	@Autowired
	private PassengerRepository passrepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);
	
	@Override
	public List<Passenger> addPassenger(List<Passenger> schedule) {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Add Schedule");
		return passrepo.saveAll(schedule);
	}

	@Override
	public Set<Passenger> viewAllPassenger() {
		// TODO Auto-generated method stub
		LOG.info("Service Inside All Passengers");
		Set<Passenger> passengers = new HashSet<>();
		passrepo.findAll().forEach(pass1 -> passengers.add(pass1));
		return passengers;
	}

	@Override
	public String removePassenger(long passengerid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Remove Passenger");
		Optional<Passenger> passenger = passrepo.findById(passengerid);
		if(passenger.isPresent()) {
			passrepo.deleteById(passengerid);
		}
		else 
			throw new ResourceNotFoundException("Wrong Passenger ID");
		return "Passenger Details Deleted";
	}

	@Override
	public List<Passenger> updatePassenger(List<Passenger> passenger) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Update Passenger Details");
		List<Passenger> passarray = new ArrayList<>();
		for(Passenger pass : passenger) {
			Optional<Passenger> pass1 = passrepo.findById(pass.getPassengerid());
			if(pass1.isPresent()) {
				passrepo.save(pass);
				passarray.add(pass);
			}
			else 
				throw new ResourceNotFoundException("Wrong Passeneer ID for : " + pass.getPassengerid());
		}
		return passarray;
	}
	
	@Override
	public Passenger viewPassenger(long passengerid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service Inside View Passenger By ID");
		Optional<Passenger> pass1 = passrepo.findById(passengerid);
		if(pass1.isPresent()) {
			return pass1.get();
		}
		else 
			throw new ResourceNotFoundException("No Details with ID : " + passengerid);
	}	
}
