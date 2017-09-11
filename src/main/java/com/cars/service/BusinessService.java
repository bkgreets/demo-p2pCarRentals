package com.cars.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cars.data.DataAccess;
import com.cars.models.CarInfo;
import com.cars.models.CarModel;
import com.cars.models.CarStockImage;
import com.cars.models.CarTransaction;
import com.cars.models.User;

@Component
public class BusinessService {
	
	
	@Autowired
	DataAccess dataInteractions;
	
	/* createUser **/
	public String createUser(User user, String userAccountInfo) throws UnknownHostException{
		String[] userAccount = splitUserAccount(userAccountInfo);
		String userId = null;
		if(null != userAccount && userAccount.length == 2){	
			userId = dataInteractions.createUser(user);
			if(null != userId && !StringUtils.isEmpty(userId))
				dataInteractions.createUserAccounts(userAccount, userId);
		}
		return userId;
	}

	
	/* validate user **/
	public Boolean checkIfUserExists(String userAccountInfo){
		return dataInteractions.checkIfUserExists(splitUserAccount(userAccountInfo));
	}
	
	/* need to get only userinfo **/
	public User basicUserData(String userName){
		return dataInteractions.getUserInfo(userName);
	}
	
	
	/* posting a new car **/
	public String registerCarToOwner(CarInfo carInfo){
		return dataInteractions.createNewCar(carInfo);
	}
	
	
	
	
	/* complete user data including cars and transactions **/
	public User userData(String userName) throws IOException{
		User user = basicUserData(userName);
		user.setCars(getUserCars(user.get_id()));
		getTransactionsOnUserID(user);
		return user;
	}

	/* get only cars on location based on longitude and latitude **/
	public List<CarInfo> listCarsBasedOnLocation(Double longitude, Double latitude) throws IOException{
		return dataInteractions.searchCars(longitude, latitude);
	}
	
	/* get carinfo based on ownername and car id.  implement later **/
	public CarInfo getCarInfo(String ownerName, String carId){
		return null;
	}
	
	/* get cars based on ownerId. **/
	public List<CarInfo> getUserCars(String ownerID) throws IOException{
		return dataInteractions.getCarsForUser(ownerID);
	}
	
	/* registering the transaction. **/
	public String registerTransaction(CarTransaction transactionInfo){
		return dataInteractions.saveTransaction(transactionInfo);
	}
	
	/* get transactions for a user **/
	public void getTransactionsOnUserID(User user) throws IOException{
		 List<CarTransaction> transactions = dataInteractions.getTransactions(user.get_id());
		 if(null != transactions)
			 splitTransactionsForUser(transactions, user);
	}
	
	
	public void loadCarsIntoDB(InputStream inputStreamfile) throws IOException{
		List<CarModel> carModels = new ArrayList<>();
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStreamfile);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			 int rowsCount = sheet.getLastRowNum();
			 for(int i = 2;i<= rowsCount;i++){
				 Row row = sheet.getRow(i);
				 System.out.println(i + " : " +row.getCell(0).toString() + " : "+row.getCell(1).toString() );
				 carModels.add(new CarModel(row.getCell(0).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString()));
			 }
			 
			 dataInteractions.insertCarDetailsIntoDB(carModels);
		
	}
	
	public List<CarModel> getCarModels() throws IOException {
		return  dataInteractions.getAllCarModels();
	}
	
	
	public void uploadImages(MultipartFile[] files) throws IOException{
		 dataInteractions.uploadImagesIntoDB(files);
	}
	
	
	public CarStockImage getImage(String make_model_year) throws IOException {
		return dataInteractions.getStockImage(make_model_year);
	}
	
	private void splitTransactionsForUser(List<CarTransaction> trasactions, User user){
          for (CarTransaction transaction : trasactions) {
			if (transaction.getOwnerId() == user.get_id()) 
				insertIntoOwnedTransactions(user, transaction);
			else
				insertIntoRentedTransactions(user, transaction);
		}
		
	}
	

	private void insertIntoRentedTransactions(User user,
			CarTransaction transaction) {
		if(null == user.getOwnedTransactions())
			user.setOwnedTransactions(new ArrayList<CarTransaction>());
		user.getOwnedTransactions().add(transaction);
	}

	private void insertIntoOwnedTransactions(User user,
			CarTransaction transaction) {
		if(null == user.getRentedTransactions())
			user.setRentedTransactions(new ArrayList<CarTransaction>());
		user.getRentedTransactions().add(transaction);
	}
	
	private String[] splitUserAccount(String userAccountInfo) {
		return StringUtils.split(userAccountInfo,':');
	}



	
	public Map<String, List<CarModel>> getCarDataBasedOnMake(String make) throws IOException {
		return sortCarModels(dataInteractions.getCarsOnMake(make));
	}
	

	public List<String> carMakes() throws IOException {
		return dataInteractions.getCarMakes();
	}


	private Map<String,List<CarModel>> sortCarModels(List<CarModel> carModels){
		Map<String,List<CarModel>> models = new HashMap<>();
		
		for(CarModel carModel: carModels){
			List<CarModel> variantModels = insertAndReturnModelObject(carModel, models);
			variantModels.add(carModel);
		}
		return models;
		
	}
	
	private List<CarModel> insertAndReturnModelObject(CarModel carModel, Map<String,List<CarModel>> models){
		
		List<CarModel> variantModels = models.get(carModel.getModel());
		if(variantModels == null){
			variantModels = new ArrayList<>();
			models.put(carModel.getModel(), variantModels);
		}
		return variantModels;
	}


	

    
	
}
