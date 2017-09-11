/**
 * 
 */
package com.cars.models;

import java.io.Serializable;

import org.jongo.marshall.jackson.oid.MongoObjectId;

/**
 * @author shantha
 *
 */
public class CarStockImage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@MongoObjectId
	private String _id;
	

	private String make_model_year;
	
	
	private byte[] image;
	
	private String imageByte64Str;

	public CarStockImage() {
	}
	
	public CarStockImage(String id, String make_model_year, String imageByte64Str){
		this.make_model_year = make_model_year;
		this.setImageByte64Str(imageByte64Str);
		this._id = id;
	}
	
	
	public CarStockImage(String make_model_year, byte[] image){
		this.make_model_year = make_model_year;
		this.image = image;
	}
	
	public String getMake_model() {
		return make_model_year;
	}

	public void setMake_model(String make_model_year) {
		this.make_model_year = make_model_year;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageByte64Str() {
		return imageByte64Str;
	}

	public void setImageByte64Str(String imageByte64Str) {
		this.imageByte64Str = imageByte64Str;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}
