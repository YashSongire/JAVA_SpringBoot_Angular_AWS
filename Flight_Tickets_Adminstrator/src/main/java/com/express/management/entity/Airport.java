package com.express.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Airport_seq")
    @SequenceGenerator(name = "Airport_seq", sequenceName = "Airport_sequence", allocationSize = 1, initialValue = 10045)
	@Column
	private long airportid;
	
	@Column(name = "Airport_Name")
	private String airportname;
	
	@Column(name = "Location")
	private String airportlocation;
	
	@Column(name = "AirPort_Code")
	private String airportcode;
	
	public Airport() {
		super();
	}
	
	public Airport(long airportid, String airportname, String airportlocation, String airportcode) {
		super();
		this.airportid = airportid;
		this.airportname = airportname;
		this.airportlocation = airportlocation;
		this.airportcode = airportcode;
	}

	public Airport(String airportname, String airportlocation, String airportcode) {
		super();
		this.airportname = airportname;
		this.airportlocation = airportlocation;
		this.airportcode = airportcode;
	}
	public long getAirportid() {
		return airportid;
	}
	public void setAirportid(long airportid) {
		this.airportid = airportid;
	}
	public String getAirportname() {
		return airportname;
	}
	public void setAirportname(String airportname) {
		this.airportname = airportname;
	}
	public String getAirportlocation() {
		return airportlocation;
	}
	public void setAirportlocation(String airportlocation) {
		this.airportlocation = airportlocation;
	}
	public String getAirportcode() {
		return airportcode;
	}
	public void setAirportcode(String airportcode) {
		this.airportcode = airportcode;
	}
}
