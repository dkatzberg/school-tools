package de.dkatzberg.schooltools.common.database;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

/**
 * The Mongo Connector handles the connection to the Mongo DB.
 * 
 * @author Daniel Katzberg
 *
 */
//TODO This is a prototype implementation!
public class MongoConnector {

	private static volatile MongoConnector MONGO_CONNECTOR;

	// TODO make a mongo host option
	// TODO Comment
	private String mongoHost = "localhost";

	// TODO make a mongo port option
	// TODO COmment
	private int mongoPort = 27017;

	/** Default MongoClient */
	private MongoClient mongoClient;

	/** Private Constructor for Singleton. */
	private MongoConnector() {
		this.mongoConnection();
	}

	/**
	 * Connect to the Mongo Database.
	 */
	private void mongoConnection() {
		this.mongoClient = MongoClients
				.create(MongoClientSettings.builder()
						.applyToClusterSettings(
								builder -> builder.hosts(Arrays.asList(new ServerAddress(mongoHost, mongoPort))))
						.build());
	}

	/**
	 * Load / Read a Mongo Collection
	 * 
	 * @param databaseName   The database name.
	 * @param collectionName The name of the collection.
	 * @return The loaded mongo collection with the documents within the collection.
	 */
	public MongoCollection<Document> getMongoCollection(String databaseName, String collectionName) {
		return this.mongoClient.getDatabase(databaseName).getCollection(collectionName);
	}

	/**
	 * Singleton Pattern. Only one instance will be generate.
	 * 
	 * @return Singleton instance of the MongoDB Connector
	 */
	public static MongoConnector getInstance() {

		if (MONGO_CONNECTOR == null) {
			synchronized (MongoConnector.class) {
				if (MONGO_CONNECTOR == null) {
					MONGO_CONNECTOR = new MongoConnector();
				}
			}
		}

		return MONGO_CONNECTOR;
	}

	/* GETTER / SETTER */
	public String getMongoHost() {
		return mongoHost;
	}

	public void setMongoHost(String mongoHost) {
		this.mongoHost = mongoHost;
	}

	public int getMongoPort() {
		return mongoPort;
	}

	public void setMongoPort(int mongoPort) {
		this.mongoPort = mongoPort;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}
}
