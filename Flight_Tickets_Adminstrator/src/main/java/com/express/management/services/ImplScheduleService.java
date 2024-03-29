package com.express.management.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.express.management.entity.Schedule;
import com.express.management.exception.ResourceNotFoundException;

public interface ImplScheduleService {

	public List<Schedule> addSchedules(List<Schedule> schedule);
	public Set<Schedule> viewAllSchedule();
	public String removeSchedule(long scheduleid) throws ResourceNotFoundException;
	public List<Schedule> updateSchedule(List<Schedule> schedule) throws ResourceNotFoundException;
	public Schedule viewSchedule(long scheduleid) throws ResourceNotFoundException;
	public List<Schedule> findAllWithCreationDateTime(LocalDateTime creationDateTime);
	public List<Schedule> findAllWithdate(Date date);
	
}
