package com.cars.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cars.models.CarInfo;
import com.cars.models.CarModel;
import com.cars.models.User;
import com.cars.service.BusinessService;

@RestController
@RequestMapping("/info")
public class NonSecuredController {
	
	@Autowired
	BusinessService business;
	

	
	@RequestMapping(value="/getCars", method = RequestMethod.GET)
	public List<CarInfo> getCarsBasedOnAddress(@RequestParam("lon") String lon, @RequestParam("lat") String lat) throws IOException{
		return business.listCarsBasedOnLocation(new Double(lon), new Double(lat));
	}


	@RequestMapping(value="/createOwner", method = RequestMethod.POST)
    public String createOwner( @RequestHeader(value="userAccount") String userAccount,  @RequestBody User user) throws UnknownHostException{
		System.out.println("the header value is :: "+userAccount);
		return business.createUser(user, userAccount);
	}

	@RequestMapping(value="/verifyUser", method = RequestMethod.GET)
	public Boolean validateUser(@RequestHeader(value="userAccount") String userAccount){
		return business.checkIfUserExists(userAccount);
	}
	
	@RequestMapping(value="/getCarModels", method = RequestMethod.GET)
	public List<CarModel> getCarModels() throws IOException{
		return business.getCarModels();
	}
	
}
