package com.cars.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cars.models.CarInfo;
import com.cars.models.CarModel;
import com.cars.models.CarStockImage;
import com.cars.models.CarTransaction;
import com.cars.models.User;
import com.cars.models.UserAccount;
import com.mongodb.DBObject;

@Component	
public class DataAccess {

	@Autowired
	MongoData mongoData;

	private final static Logger logger = LoggerFactory.getLogger(DataAccess.class);
	
	
	public String createUser(User owner) throws UnknownHostException{
		MongoCollection collection = mongoData.getUserCollection();
		collection.save(owner);
		return owner.get_id();
	}
	
	public User getUserInfo(String name){
		MongoCollection collection = mongoData.getUserCollection();
		return collection.findOne("{userName:#}", name).as(User.class); 
	}
	
	public String getUserID(String name){
		MongoCollection collection = mongoData.getUserCollection();
		ObjectId ObjectId = collection.findOne("{name:#}", name).projection("{_id : 1, total : 1}").as(ObjectId.class);
		return ObjectId.toString();
	}
	
	public String createNewCar(CarInfo carInfo){
		MongoCollection collection = mongoData.getCarsCollection();
		collection.save(carInfo);
		return carInfo.get_id();
	}

	public void createUserAccounts(String[] strArray, String userID) {
		MongoCollection collection = mongoData.getUserAccountsCollection();
		System.out.println("str array is :: "+strArray);
		collection.save(new UserAccount(strArray, userID));
		System.out.println("new user account alias id created");
		
	}

	public Boolean checkIfUserExists(String[] strArray){
		MongoCollection collection = mongoData.getUserAccountsCollection();
		long count = collection.count("{$and :[{userName:#},{pwd:#},{userId:{$exists: true}}]}",strArray[0], strArray[1]);
		return (count == 1) ? true : false;
	}

	public List<CarInfo> getCarsForUser(String ownerID) throws IOException {
		
		MongoCollection collection = mongoData.getCarsCollection();
		MongoCursor<CarInfo> cars = collection.find("{ownerId:#}", ownerID).as(CarInfo.class);
		return (cars.count()>0) ? loopOnIterator(cars, new ArrayList<CarInfo>()) : null;
		
	}
	
	

	public String saveTransaction(CarTransaction transactionInfo) {
		MongoCollection collection = mongoData.getUserTransactionsCollection();
		collection.save(transactionInfo);
		return transactionInfo.get_id();
	}

	public List<CarTransaction> getTransactions(String searchID) throws IOException {
		MongoCollection collection = mongoData.getUserTransactionsCollection();
		MongoCursor<CarTransaction> trasactions = collection.find("{$or :[{ownerId:#},{renterId:#}]}",searchID, searchID).as(CarTransaction.class);
		return (trasactions.count()>0) ? loopOnIterator(trasactions, new ArrayList<CarTransaction>()) : null;
	}

	public List<CarInfo> searchCars(Double longitude, Double latitude) throws IOException {
		MongoCollection collection = mongoData.getCarsCollection();
		MongoCursor<CarInfo> cars = collection.find("{locationInfo:{ $near :{$geometry: { type: \"Point\",  coordinates: [ #, # ] },$minDistance: 0,$maxDistance: 1000}}}", longitude, latitude).as(CarInfo.class);
		return (cars.count()>0) ? loopOnIterator(cars, new ArrayList<CarInfo>()) : null;
	}

	public void insertCarDetailsIntoDB(List<CarModel> carDetails){
		MongoCollection collection = mongoData.getCarDetailInfoCollection();
		 collection.insert(carDetails.toArray());
	}
	
	public List<CarModel> getAllCarModels() throws IOException {
		MongoCollection collection = mongoData.getCarDetailInfoCollection();
		MongoCursor<CarModel> models = collection.find().as(CarModel.class);
		return (models.count()>0) ? loopOnIterator(models, new ArrayList<CarModel>()) : null;
	}
	
	public void uploadImagesIntoDB(MultipartFile[] files) throws IOException{
		MongoCollection collection = mongoData.getCarImagesCollectionName();
		List<CarStockImage> carStockImages = new ArrayList<>();
		 
		for(MultipartFile file: files){
			logger.info("inserting file :: "+file.getOriginalFilename());
			String byteStr = Base64.encodeBase64String(file.getBytes());
			byte[] strInBytes = compressString(byteStr);
			carStockImages.add(new CarStockImage(StringUtils.split(file.getOriginalFilename(), '.')[0], strInBytes));
		}
		collection.insert(carStockImages.toArray());
	}
	
	public CarStockImage getStockImage(String make_model_year)
			throws IOException {
		MongoCollection collection = mongoData.getCarImagesCollectionName();
		CarStockImage stockImg = collection.findOne("{make_model_year:#}",
				make_model_year).map(new ResultHandler<CarStockImage>() {

			@Override
			public CarStockImage map(DBObject result) {

				byte[] imageInBytes = (byte[]) result.get("image");

				String imageInSTr = null;
				try {
					imageInSTr = decompressToString(imageInBytes);
				} catch (IOException e) {
					e.printStackTrace();
				}

				return new CarStockImage(result.get("_id").toString(), result
						.get("make_model_year").toString(), imageInSTr);
			}

		});
		return stockImg;
	}
	
	
	
	public List<CarModel> getCarsOnMake(String make) throws IOException {
		MongoCollection collection = mongoData.getCarDetailInfoCollection();
		MongoCursor<CarModel> models = collection.find("{make:#}", make).as(CarModel.class);
		return (models.count()>0) ? loopOnIterator(models, new ArrayList<CarModel>()) : null;
	}
	
	
	public List<String> getCarMakes() throws IOException {
		MongoCollection collection = mongoData.getCarDetailInfoCollection();
		List<String> results = collection.distinct("make").as(String.class);
		return results;
	}
	
	private  <T> List<T>  loopOnIterator(MongoCursor<T> cursor, List<T> listToAdd) throws IOException{
		Iterator<T> iterator = cursor.iterator();
		while(iterator.hasNext()){
			listToAdd.add(iterator.next());
		}
		cursor.close();
		return listToAdd;
	}

	private byte[] compressString(String src) throws IOException{
		byte[] Str = src.getBytes();
		 logger.info("thse size is :: "+Str.length);
		ByteArrayOutputStream rstBao = new ByteArrayOutputStream();
	    GZIPOutputStream zos = new GZIPOutputStream(rstBao);
	    zos.write(src.getBytes());
	    IOUtils.closeQuietly(zos);
	    byte[] bytes = rstBao.toByteArray();
	    IOUtils.closeQuietly(rstBao);
        
	    logger.info("thse size is :: "+bytes.length);
	    return bytes;
	    
	}
	
	private String decompressToString(byte[] bytes) throws IOException{
		GZIPInputStream zi = new GZIPInputStream(new ByteArrayInputStream(bytes));
		String result = IOUtils.toString(zi);
		IOUtils.closeQuietly(zi);
		return result;
	}

	

	
	
	
}
