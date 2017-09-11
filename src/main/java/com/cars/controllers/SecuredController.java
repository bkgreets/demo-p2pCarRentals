package com.cars.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cars.models.CarInfo;
import com.cars.models.CarModel;
import com.cars.models.CarStockImage;
import com.cars.models.CarTransaction;
import com.cars.models.User;
import com.cars.service.BusinessService;



@RestController
@RequestMapping("/api")
public class SecuredController {

	@Autowired
	BusinessService business;
	
	
	

	
	@RequestMapping(value="/getUser/{userName}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userName") String userName) throws UnknownHostException{
		return business.basicUserData(userName);
	}
	
	@RequestMapping(value="/getUserInDetail/{userName}", method = RequestMethod.GET)
	public User getUserInfoInDetail(@PathVariable("userName") String userName) throws IOException{
		return business.userData(userName);
	}

	
	@RequestMapping(value="/postNewCar", method = RequestMethod.POST)
	public String postNewCar(@RequestBody CarInfo carInfo) throws UnknownHostException{
		return business.registerCarToOwner(carInfo);
	}
	
	@RequestMapping(value="/getCars/{ownerId}", method = RequestMethod.GET)
	public List<CarInfo> getCarsForUser(@PathVariable("ownerId") String ownerId) throws IOException{
		return business.getUserCars(ownerId);
	}
	
	@RequestMapping(value="/postNewTransaction", method = RequestMethod.POST)
    public String registerTransaction(@RequestBody CarTransaction  transactionInfo){
	   return business.registerTransaction(transactionInfo);
    }
	
	
	@RequestMapping(value="/uploadCarsData", method = RequestMethod.POST)
    public void uploadFile(@RequestParam(value = "file", required=true) MultipartFile file) throws IOException{
	   System.out.println("file is being sent ith file name as :: "+file.getOriginalFilename());
	   
	   business.loadCarsIntoDB(file.getInputStream());
	   
    }
	
	// there is a possibility that you client needs to send multiple images instead of folder.
	@RequestMapping(value="/uploadCarsImage", method = RequestMethod.POST)
    public void uploadImages(@RequestParam(value="file", required=true) MultipartFile[] files) throws IOException{
		business.uploadImages(files);
    }

	@RequestMapping(value="/getCarMake", method = RequestMethod.GET)
	public List<String> getCarManfacturers() throws IOException{
		return business.carMakes();
	}
  
	
	
	@RequestMapping(value="/getCarMetaData/{make}", method = RequestMethod.GET)
	public Map<String,List<CarModel>> getCarMetadata(@PathVariable("make") String make) throws IOException{
		return business.getCarDataBasedOnMake(make);
		
	}
	
	@RequestMapping(value="/getImageForCar/{make_model_year}" , method = RequestMethod.GET)
	public CarStockImage getImageForCar(@PathVariable("make_model_year") String make_model_year) throws IOException{
		return business.getImage(make_model_year);
	}
	
	
}

