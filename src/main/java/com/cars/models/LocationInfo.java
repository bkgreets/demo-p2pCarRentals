package com.cars.models;

import java.io.Serializable;

public class LocationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type = "Point";
	
	private Double[] coordinates;
	
	private int zipcode;
	
	public LocationInfo() {// TODO Auto-generated constructor stub
	}
	
	public LocationInfo(Double longitude, Double latitude, int zipcode) {
		this.coordinates = new Double[]{longitude, latitude};
		this.setZipcode(zipcode);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	
}
