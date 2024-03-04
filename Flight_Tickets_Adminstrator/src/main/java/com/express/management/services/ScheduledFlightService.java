package com.express.management.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.express.management.entity.Airport;
import com.express.management.entity.Flight;
import com.express.management.entity.Schedule;
import com.express.management.entity.ScheduledFlight;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.AirportRepository;
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
	@Autowired
	private AirportRepository airrepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);
	
	@Override
	public List<ScheduledFlight> addScheduledFlight(List<ScheduledFlight> scheduledflight) {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Add ScheduleFlight");
		   List<ScheduledFlight> scheduleflights = new ArrayList<>();
		   for(ScheduledFlight sch : scheduledflight ) {
			   LOG.info("Info" +sch.getSchedule().getSource().getAirportlocation() 
					   + sch.getSchedule().getDestination().getAirportlocation()
					   + sch.getFlight().getFlightId());
			   Optional<Airport> source = airrepo.findByairportlocation(sch.getSchedule().getSource().getAirportlocation());
			   Optional<Airport> destination = airrepo.findByairportlocation(sch.getSchedule().getDestination().getAirportlocation());
			   Optional<Flight> flight = flrepo.findById(sch.getFlight().getFlightId());
			   if(source.isPresent() && destination.isPresent() && flight.isPresent()) {
				   Schedule schedule = new Schedule(source.get(),destination.get(), sch.getSchedule().getDateAndTimeOfArrival(), sch.getSchedule().getDateAndTimeOfDeparture());
				   ScheduledFlight schfl = new ScheduledFlight(flight.get(), schedule, sch.getAvailableSeats());
			
				   schflrepo.save(schfl);
				   scheduleflights.add(schfl);
			   }
			   else
				   throw new ResourceNotFoundException("No Values In Database" + source.get().getAirportlocation() 
						   + destination.get().getAirportlocation()
						   + flight.get().getFlightId()
						   );
		   }
		    return scheduleflights;
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

	//All FLights By Location and Date
	@Override
	public List<ScheduledFlight> viewAllScheduledflightsbylocationanddate(String source, String destination,
			Date date) {
		// TODO Auto-generated method stub
		LOG.info("Service Inside Get ScheduleFlight" + source+ " " +destination + " "+ date);
		Optional<List<ScheduledFlight>> schfldata = schflrepo.findFlightsBySourceAndDestinationAndDeparture(source, destination, date);
		if(schfldata.isPresent()) {
			return schfldata.get();	
		}
		else 
		throw new ResourceNotFoundException("No DATA for Mentioned Locations");
	}
}
