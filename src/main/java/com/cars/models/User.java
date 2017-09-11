package com.cars.models;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.jongo.marshall.jackson.oid.MongoObjectId;

public class User implements Serializable {
	
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@MongoObjectId
	    private String _id;
	 	
		@NotNull
	 	private String name;
	 	
		
		private String userName;
		
	 	private String desc;
	 	
	 	@NotNull
	 	private String contactNum;
	 	
	 	private String emailAddress;
	 	
	 	private Address address;
	 	
	 	private List<CarTransaction> rentedTransactions;
	 	
	 	private List<CarTransaction> ownedTransactions;
	 	
	 	private List<CarInfo> cars;

	 	private String userLoginType = "regular";
	 	
	 	
	 	
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmailAddress() {
			return emailAddress;
		}
		
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public List<CarTransaction> getRentedTransactions() {
			return rentedTransactions;
		}

		public void setRentedTransactions(List<CarTransaction> rentedTransactions) {
			this.rentedTransactions = rentedTransactions;
		}

		public List<CarTransaction> getOwnedTransactions() {
			return ownedTransactions;
		}

		public void setOwnedTransactions(List<CarTransaction> ownedTransactions) {
			this.ownedTransactions = ownedTransactions;
		}

		public String getContactNum() {
			return contactNum;
		}

		public void setContactNum(String contactNum) {
			this.contactNum = contactNum;
		}

		public  Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public List<CarInfo> getCars() {
			return cars;
		}

		public void setCars(List<CarInfo> cars) {
			this.cars = cars;
		}

		public String getUserLoginType() {
			return userLoginType;
		}

		public void setUserLoginType(String userLoginType) {
			this.userLoginType = userLoginType;
		}
	 	
	 	

}
