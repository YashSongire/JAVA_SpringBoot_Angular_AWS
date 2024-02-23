package com.express.management.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Bookings {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_sequence", allocationSize = 1, initialValue = 800200100)
	private long bookingid;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Column
	private Date bookingdate;
	
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Passenger> passengers;
	
	@Column
	private float bookingcost;
	
	@ManyToOne
	@JoinColumn(name= "schfl_id")
	private ScheduledFlight scheduledflight;

	public long getBookingid() {
		return bookingid;
	}

	public void setBookingid(long bookingid) {
		this.bookingid = bookingid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public float getBookingcost() {
		return bookingcost;
	}

	public void setBookingcost(float bookingcost) {
		this.bookingcost = bookingcost;
	}

	public ScheduledFlight getScheduledflight() {
		return scheduledflight;
	}

	public void setScheduledflight(ScheduledFlight scheduledflight) {
		this.scheduledflight = scheduledflight;
	}

	public Bookings(long bookingid, User user, Date bookingdate, List<Passenger> passengers, float bookingcost,
			ScheduledFlight scheduledflight) {
		super();
		this.bookingid = bookingid;
		this.user = user;
		this.bookingdate = bookingdate;
		this.passengers = passengers;
		this.bookingcost = bookingcost;
		this.scheduledflight = scheduledflight;
	}

	public Bookings(User user, Date bookingdate, List<Passenger> passengers, float bookingcost,
			ScheduledFlight scheduledflight) {
		super();
		this.user = user;
		this.bookingdate = bookingdate;
		this.passengers = passengers;
		this.bookingcost = bookingcost;
		this.scheduledflight = scheduledflight;
	}

	public Bookings() {
		super();
	} 
}
