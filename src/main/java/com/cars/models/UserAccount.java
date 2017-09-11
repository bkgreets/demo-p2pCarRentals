package com.cars.models;

import java.io.Serializable;

public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String pwd;
	
	private String userId;
	
	public UserAccount(String[] strArray, String userId) {
		if(strArray!=null && strArray.length==2){
		this.userName=strArray[0];
		this.pwd=strArray[1];
		this.userId=userId;
		}
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
