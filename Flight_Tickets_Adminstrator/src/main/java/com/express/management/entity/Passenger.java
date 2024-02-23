package com.express.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;

@Entity
@Table
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passenger_gen")
	@SequenceGenerator(name = "passenger_gen", sequenceName = "Passenger_seq", allocationSize = 1, initialValue = 130100)
	private long passengerid;
	
	@Column
	private String passengerName;
	
	@Column
	private int passengerAge;
	
	@Column
	@Valid
	@Max(value = 15,message = "Weight Should Be Below 15kg")
	private double luggage;

	public long getPassengerid() {
		return passengerid;
	}

	public void setPassengerid(long passengerid) {
		this.passengerid = passengerid;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public double getLuggage() {
		return luggage;
	}

	public void setLuggage(double luggage) {
		this.luggage = luggage;
	}

	public Passenger(long passengerid, String passengerName, int passengerAge, double luggage) {
		super();
		this.passengerid = passengerid;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.luggage = luggage;
	}

	public Passenger(String passengerName, int passengerAge, double luggage) {
		super();
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.luggage = luggage;
	}

	public Passenger() {
		super();
	}
}
