package com.express.management.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.express.management.entity.ScheduledFlight;
import com.express.management.exception.ResourceNotFoundException;

public interface ImplScheduleFlightService {

	
	public List<ScheduledFlight> addScheduledFlight(List<ScheduledFlight> scheduledflight);
	public Set<ScheduledFlight> viewAllScheduledFlight();
	public String removeScheduledFlight(long scheduledflightid) throws ResourceNotFoundException;
	public List<ScheduledFlight> updateScheduledFlight(List<ScheduledFlight> scheduledflight) throws ResourceNotFoundException;
	public ScheduledFlight viewScheduledFlight(long scheduledflightid) throws ResourceNotFoundException;
	public List<ScheduledFlight> viewAllScheduledflightsbylocationanddate(String source, String destination, Date date);
}
