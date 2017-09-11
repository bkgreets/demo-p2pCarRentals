package com.cars.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.jongo.marshall.jackson.oid.MongoObjectId;


/**
 * 
 *  still need to change to add carinfo.
 *  may be instead of duplicate info, we can store the reference if of car model 
 *  and image id if it is a stock photo.
 *  
 *  
 *  */
public class CarInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@MongoObjectId
	private String _id;
	
	private String carName;
	
	private LocationInfo locationInfo;
	
	
	
	@NotNull
	private String ownerId;
	
	@NotNull
	private String ownerUserName;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public LocationInfo getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(LocationInfo locationInfo) {
		this.locationInfo = locationInfo;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	
	
	//not sure yet on how to store images
	//private String carImage;
	
	

}
