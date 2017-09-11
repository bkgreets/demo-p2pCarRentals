package com.cars.models;

import java.io.Serializable;

import org.jongo.marshall.jackson.oid.MongoObjectId;

public class CarTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@MongoObjectId
    private String _id;
	
	private String renterId;
	
	private String ownerId;
	
	private String carId;
	
	private String ownerUserName;
	
	private String renterUserName;

	
	
	

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getRenterId() {
		return renterId;
	}

	public void setRenterId(String renterId) {
		this.renterId = renterId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getRenterUserName() {
		return renterUserName;
	}

	public void setRenterUserName(String renterUserName) {
		this.renterUserName = renterUserName;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
	
	

}
