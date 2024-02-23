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
public class Flight {
	
	@Id
	@Column(name = "Flight_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_seq")
    @SequenceGenerator(name = "flight_seq", sequenceName = "flight_sequence", allocationSize = 1, initialValue = 45000)
	private long flightId;
	
	@Column(name = "Carrier_Name")
	private String carrierName;
	
	@Column(name = "Flight_Model")
	private String flightModel;
	
	@Column(name = "Total_Capacity")
	private int seatCapacity;
	
	public Flight(long flightId, String carrierName, String flightModel, int seatCapacity) {
		super();
		this.flightId = flightId;
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatCapacity = seatCapacity;
	}
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flight(String carrierName, String flightModel, int seatCapacity) {
		super();
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatCapacity = seatCapacity;
	}
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getFlightModel() {
		return flightModel;
	}
	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
}
