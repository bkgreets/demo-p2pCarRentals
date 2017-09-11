package com.cars.data;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Component
public class MongoData {

	private MongoCollection userCollection;

	private MongoCollection carsCollection;

	private MongoCollection userAccountsCollection;

	private MongoCollection userTransactionsCollection;

	private MongoCollection carDetailInfoCollection;
	
	private MongoCollection carImagesCollectionName;

	private Jongo jongo;

	@Autowired
	public MongoData(
			@Value("${mongoLoc}") String mongoUrl,
			@Value("${userCollectionName}") String userCollectionName,
			@Value("${dbName}") String dbName,
			@Value("${carsCollectionName}") String carsCollectionName,
			@Value("${userAccountCollectionName}") String userAccountCollectionName,
			@Value("${userTransactionCollectionName}") String userTransactionCollectionName,
			@Value("${carDetailInfoCollectionName}") String carDetailInfoCollectionName,
			@Value("${carImagesCollectionName}") String carImagesCollectionName)
			throws UnknownHostException {

		setJongo(mongoUrl, dbName);

		// set indexes and collections.

		initializeUserCollection(userCollectionName);
		initializeCarsCollection(carsCollectionName);
		initialiazeUserAccountsCollectionName(userAccountCollectionName);
		initialiazeUserTransactionsCollection(userTransactionCollectionName);
		initialiazeCarDetailInfoCollection(carDetailInfoCollectionName);
		initialiazeCarImagesCollectionName(carImagesCollectionName);

	}

	

	public void setJongo(String mongoUrl, String dbName)
			throws UnknownHostException {

		MongoClientURI uri = new MongoClientURI(mongoUrl);

		MongoClient client = new MongoClient(uri);

		DB db = client.getDB(dbName);

		this.jongo = new Jongo(db);

	}

	private void initialiazeCarDetailInfoCollection(
			String carDetailInfoCollectionName) {

		this.carDetailInfoCollection = jongo
				.getCollection(carDetailInfoCollectionName);

	}

	private void initialiazeCarImagesCollectionName(
			String carImagesCollectionName) {
		this.carImagesCollectionName = jongo.getCollection(carImagesCollectionName);
		
	}
	
	private void initializeUserCollection(String userCollectionName) {

		this.userCollection = jongo.getCollection(userCollectionName);

	}

	public void initializeCarsCollection(String carsCollectionName) {

		this.carsCollection = jongo.getCollection(carsCollectionName);

	}

	public void initialiazeUserAccountsCollectionName(
			String userAccountCollectionName) {

		this.userAccountsCollection = jongo
				.getCollection(userAccountCollectionName);

	}

	private void initialiazeUserTransactionsCollection(
			String userTransactionCollectionName) {

		this.userTransactionsCollection = jongo
				.getCollection(userTransactionCollectionName);

	}

	public MongoCollection getUserCollection() {

		return userCollection;

	}

	public MongoCollection getCarsCollection() {

		return carsCollection;

	}

	public MongoCollection getUserAccountsCollection() {

		return userAccountsCollection;

	}

	public MongoCollection getUserTransactionsCollection() {

		return userTransactionsCollection;

	}

	public MongoCollection getCarDetailInfoCollection() {

		return carDetailInfoCollection;

	}



	public MongoCollection getCarImagesCollectionName() {
		return carImagesCollectionName;
	}



	

	// public void setUserTransactionsCollection(MongoCollection
	// userTransactionsCollection) {

	// this.userTransactionsCollection = userTransactionsCollection;

	// }

	//

}