package foo.dbgroup.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnect {
	
	MongoClient mongoClient;
	DB db;

	public MongoConnect() {
		super();
		try {
			mongoClient = new MongoClient( "localhost" );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = mongoClient.getDB( "RDFstruct" );
	}
	
	public DB getDb(){
		return db;
	}
	
	
	
	

}
