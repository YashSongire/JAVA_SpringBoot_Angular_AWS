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

import com.express.management.entity.Flight;
import com.express.management.entity.Schedule;
import com.express.management.entity.ScheduledFlight;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.FlightRepository;
import com.express.management.repository.ScheduleFlightRepository;
import com.express.management.repository.ScheduleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ScheduledFlightService implements ImplScheduleFlightService{

	@Autowired
	private ScheduleFlightRepository schflrepo; 
	@Autowired
	private ScheduleRepository schedulerepo;
	@Autowired
	private FlightRepository flrepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);
	
	@Override
	public List<ScheduledFlight> addScheduledFlight(List<ScheduledFlight> scheduledflight) {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Add ScheduleFlight");
		   List<ScheduledFlight> newScheduleFlight = new ArrayList<>();
		    for (ScheduledFlight schfl : scheduledflight) {
		        Optional<Schedule> schedule = schedulerepo.findById(schfl.getSchedule().getScheduleid());
		        Optional<Flight> flight = flrepo.findById(schfl.getFlight().getFlightId());
		        	if (schedule.isPresent() && flight.isPresent()){
		        		ScheduledFlight schedulefl = new ScheduledFlight(flight.get(),schedule.get(),schfl.getAvailableSeats());
		        		newScheduleFlight.add(schedulefl);
		        		schflrepo.saveAll(newScheduleFlight);
		        		}
		        	else {
		        		throw new ResourceNotFoundException("Wrong Flight IDs: " + schfl.getFlight().getFlightId() + " or Wrong Schdeule ID " + schfl.getSchedule().getScheduleid());
		        		}
		        	}
		    return newScheduleFlight;
	}

	@Override
	public Set<ScheduledFlight> viewAllScheduledFlight() {
		// TODO Auto-generated method stub
		LOG.info("Service Inside All ScheduleFlight");
		Set<ScheduledFlight> schfldata = new HashSet<>();
		schflrepo.findAll().forEach(schfl1 -> schfldata.add(schfl1));
		return schfldata;
	}

	@Override
	public String removeScheduledFlight(long scheduledflightid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Delete ScheduleFlight" + scheduledflightid);
		Optional<ScheduledFlight> deleteschfl = schflrepo.findById(scheduledflightid);
		if(deleteschfl.isPresent()) {
			schflrepo.deleteById(scheduledflightid);
		}
		else
			throw new ResourceNotFoundException("scheduledflight Doesn't Exist for ID : " + scheduledflightid);
		
		return "Scheduledflight with ID " + scheduledflightid + " Deleted"; 
	}

	@Override
	public List<ScheduledFlight> updateScheduledFlight(List<ScheduledFlight> scheduledflight)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Update ScheduleFlight");
		List<ScheduledFlight> newScheduleFlight = new ArrayList<>();
	    for (ScheduledFlight schfl : scheduledflight) {
	    	Optional<ScheduledFlight> updateschfl = schflrepo.findById(schfl.getScheduleflightid());
	    	if(updateschfl.isPresent()) {
	    		Optional<Schedule> schedule = schedulerepo.findById(schfl.getSchedule().getScheduleid());
		        Optional<Flight> flight = flrepo.findById(schfl.getFlight().getFlightId());
		        	if (schedule.isPresent() && flight.isPresent()){
		        		ScheduledFlight schedulefl = new ScheduledFlight(schfl.getScheduleflightid(),flight.get(),schedule.get(),schfl.getAvailableSeats());
		        		newScheduleFlight.add(schedulefl);
		        		schflrepo.saveAll(newScheduleFlight);
		        		}
		        	else {
		        		throw new ResourceNotFoundException("Wrong Flight ID: " + schfl.getFlight().getFlightId() + " or Wrong Schdeule ID: " + schfl.getSchedule().getScheduleid());
		        		}
	    	}
	    	else 
	    		throw new ResourceNotFoundException("Wrong SchduleFlight ID : " + schfl.getScheduleflightid());
	    	}
	    return newScheduleFlight;
	    }
	
	@Override
	public ScheduledFlight viewScheduledFlight(long scheduledflightid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Get ScheduleFlight");
		Optional<ScheduledFlight> schfldata = schflrepo.findById(scheduledflightid);
		if(schfldata.isPresent()) {
			return schfldata.get();	
		}
		else 
		throw new ResourceNotFoundException("No DATA for Mentioned ID");
	}
}