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
import com.express.management.entity.Schedule;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.AirportRepository;
import com.express.management.repository.ScheduleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ScheduleServices implements ImplScheduleService{
	
	@Autowired
	private ScheduleRepository schedulerepo;
	@Autowired
	private AirportRepository airrepo;

	
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleServices.class);

	// Create Schedules
	@Override
	public List<Schedule> addSchedules(List<Schedule> schedules) {
	    LOG.info("Services - Create Schedule");
	    List<Schedule> newSchedules = new ArrayList<>();
	    for (Schedule schedule : schedules) {
	    	if(schedule.getSource().getAirportlocation() != schedule.getDestination().getAirportlocation()) {
	        Optional<Airport> sourceOpt = airrepo.findByairportlocation(schedule.getSource().getAirportlocation());
	        Optional<Airport> destinationOpt = airrepo.findByairportlocation(schedule.getDestination().getAirportlocation());
	        	if (sourceOpt.isPresent() && destinationOpt.isPresent()) {
	        		Schedule schedulenew = new Schedule(sourceOpt.get(),destinationOpt.get(),schedule.getDateAndTimeOfArrival(),schedule.getDateAndTimeOfDeparture());
	        		newSchedules.add(schedulenew);
	        		schedulerepo.saveAll(newSchedules);
	        		}
	        	else {
	        		throw new ResourceNotFoundException("Wrong Airport: " + schedule.getSource().getAirportlocation() + " or " + schedule.getDestination().getAirportlocation());
	        		}
	        	}
	    	else
	    		throw new ResourceNotFoundException("Source and Destination Cannot Be Same");
	    	}
	    return newSchedules;
	    }

	// Get All Schedules
	@Override
	public Set<Schedule> viewAllSchedule() {
		// TODO Auto-generated method stub
		LOG.info("Services - Get Schedules ");
		Set<Schedule> schedules = new HashSet<Schedule>();
		schedulerepo.findAll().forEach(sch1 -> schedules.add(sch1));
		return schedules;
	}

	// Delete Schedule Entry
	@Override
	public String removeSchedule(long scheduleid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Services - Delete Schedules by ID" +scheduleid);
		Optional<Schedule> sch  = schedulerepo.findById(scheduleid);
		if(sch.isPresent()) {
			schedulerepo.deleteById(scheduleid);
			return "Schedules Deleted OF ID: "+ scheduleid;
		}
		else
			throw new ResourceNotFoundException("No ID FOUND");
	}
	
	// Update in Schedule
	@Override
	public List<Schedule> updateSchedule(List<Schedule> schedule) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Services - Update Schedules");
		List<Schedule> newSchedules = new ArrayList<>();
	    for (Schedule sch : schedule) {
	    	Optional<Schedule> schnew = schedulerepo.findById(sch.getScheduleid()); 
	    	if(schnew.isPresent()) {	
	    		if(sch.getSource().getAirportid() != sch.getDestination().getAirportid()) {
	    			Optional<Airport> sourceOpt = airrepo.findById(sch.getSource().getAirportid());
	    			Optional<Airport> destinationOpt = airrepo.findById(sch.getDestination().getAirportid());
	    			if (sourceOpt.isPresent() && destinationOpt.isPresent()) {
	    				Schedule schedulenew = new Schedule(sch.getScheduleid(),sourceOpt.get(),destinationOpt.get(),sch.getDateAndTimeOfArrival(),sch.getDateAndTimeOfDeparture());
	    				newSchedules.add(schedulenew);
	    				schedulerepo.saveAll(newSchedules);
	    				}
	    			else {
	    				throw new ResourceNotFoundException("Wrong Airport IDs: " + sch.getSource().getAirportid() + " or " + sch.getDestination().getAirportid());
	    				}
	    			}
	    		else
	    			throw new ResourceNotFoundException("Source and Destination Cannot Be Same");
	    		}
	    	else
	    		throw new ResourceNotFoundException("Scheduled Not Found for ID : " + sch.getScheduleid());
	    	}
	    return newSchedules;
	    }
	
	// Get Schedule By ID
	@Override
	public Schedule viewSchedule(long scheduleid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Services - Get Schedule of Id - " +scheduleid);
		Optional<Schedule> schdata = schedulerepo.findById(scheduleid);
		if(schdata.isPresent()) {
			return schdata.get();	
		}
		else 
		throw new ResourceNotFoundException("No DATA for Mentioned ID");
	}

	@Override
	public List<Schedule> findAllWithCreationDateTime(Date creationDateTime) {
		LOG.info("Services - Get Schedule of Id - " +creationDateTime);
		Optional<List<Schedule>> schdata = schedulerepo.findAllWithCreationDateTime(creationDateTime);
		if(schdata.isPresent()) {
			return schdata.get();
		}
		else
			throw new ResourceNotFoundException("No DATA for Mentioned Date");
		
	}

	@Override
	public List<Schedule> findAllWithdate(Date date) {
		// TODO Auto-generated method stub
		LOG.info("Services - Get Schedule of Id - " +date);
		Optional<List<Schedule>> schdata = schedulerepo.findByDate(date);
		if(schdata.isPresent()) {
			return schdata.get();
		}
		else
			throw new ResourceNotFoundException("No DATA for Mentioned Date");
		
	}
}
