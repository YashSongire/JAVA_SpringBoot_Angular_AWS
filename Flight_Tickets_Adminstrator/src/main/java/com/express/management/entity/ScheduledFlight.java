package com.express.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class ScheduledFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schfl_seq")
    @SequenceGenerator(name = "schfl_seq", sequenceName = "SchFl_sequence", allocationSize = 1, initialValue = 49988800)
	private long scheduleflightid;
	
	@ManyToOne
	@JoinColumn(name = "Flight")
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "Schedule")
	private Schedule schedule;
	
	@Column
	private int availableSeats;

	public long getScheduleflightid() {
		return scheduleflightid;
	}

	public void setScheduleflightid(long scheduleflightid) {
		this.scheduleflightid = scheduleflightid;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public ScheduledFlight(long scheduleflightid, Flight flight, Schedule schedule, int availableSeats) {
		super();
		this.scheduleflightid = scheduleflightid;
		this.flight = flight;
		this.schedule = schedule;
		this.availableSeats = availableSeats;
	}

	public ScheduledFlight(Flight flight, Schedule schedule, int availableSeats) {
		super();
		this.flight = flight;
		this.schedule = schedule;
		this.availableSeats = availableSeats;
	}

	public ScheduledFlight() {
		super();
	}
}
