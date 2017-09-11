package com.cars.models;

import java.io.Serializable;

import org.jongo.marshall.jackson.oid.MongoObjectId;

public class CarModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@MongoObjectId
	private String _id;
	
	private String make;
	
	private String model;
	
	private String variant;
	
	private String price;

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public CarModel(String make, String model, String variant, String price) {
		super();
		this.make = make;
		this.model = model;
		this.variant = variant;
		this.price = price;
	}
	
	public CarModel() {
		
	}

}
