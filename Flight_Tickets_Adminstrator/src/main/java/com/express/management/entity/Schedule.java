package com.express.management.entity;

import java.util.Date;

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
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Schedule_seq")
    @SequenceGenerator(name = "Schedule_seq", sequenceName = "Sch_sequence", allocationSize = 1, initialValue = 998800)
    private long scheduleid;

    @ManyToOne
    @JoinColumn(name= "Source")
    private Airport source;

    @ManyToOne
    @JoinColumn(name = "Destination")
    private Airport destination;

    @Column(name = "Arrivals")
    private Date dateAndTimeOfArrival;

    @Column(name = "Departures")
    private Date dateAndTimeOfDeparture;

	public long getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(Long scheduleid) {
		this.scheduleid = scheduleid;
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Date getDateAndTimeOfArrival() {
		return dateAndTimeOfArrival;
	}

	public void setDateAndTimeOfArrival(Date dateAndTimeOfArrival) {
		this.dateAndTimeOfArrival = dateAndTimeOfArrival;
	}

	public Date getDateAndTimeOfDeparture() {
		return dateAndTimeOfDeparture;
	}

	public void setDateAndTimeOfDeparture(Date dateAndTimeOfDeparture) {
		this.dateAndTimeOfDeparture = dateAndTimeOfDeparture;
	}

	public Schedule(Long scheduleid, Airport sourceAirport, Airport destinationAirport, Date dateAndTimeOfArrival,
			Date dateAndTimeOfDeparture) {
		super();
		this.scheduleid = scheduleid;
		this.source = sourceAirport;
		this.destination = destinationAirport;
		this.dateAndTimeOfArrival = dateAndTimeOfArrival;
		this.dateAndTimeOfDeparture = dateAndTimeOfDeparture;
	}

	public Schedule(Airport sourceAirport, Airport destinationAirport, Date dateAndTimeOfArrival,
			Date dateAndTimeOfDeparture) {
		super();
		this.source = sourceAirport;
		this.destination = destinationAirport;
		this.dateAndTimeOfArrival = dateAndTimeOfArrival;
		this.dateAndTimeOfDeparture = dateAndTimeOfDeparture;
	}

	public Schedule() {
		super();
	}
}