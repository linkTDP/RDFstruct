package foo.dbgroup.RDFstruct;

import java.util.Collection;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import foo.dbgroup.mongo.MongoConnect;

public class TestAggregate {
//[{$unwind:'$queryResult'},{$group:{_id:"$queryResult.error",count:{$sum:1}}},{$sort:{count:-1}}]
	public static void main(String[] args) {
	
	MongoConnect con=new MongoConnect();
	DB db = con.getDb();
	
	DBCollection result=db.getCollection("DatasetResult");
	
	DBObject unwind=new BasicDBObject("$unwind","$queryResult");
	
	DBObject groupfield=new BasicDBObject("_id","$queryResult.error");
	
	groupfield.put("count", new BasicDBObject("$sum",1));
	
	DBObject group=new BasicDBObject("$group",groupfield);
	
	DBObject sort=new BasicDBObject("$sort",new BasicDBObject("count",-1));
	
	AggregationOutput output=result.aggregate(unwind, group, sort);
	
//	System.out.println(output);
//	
//	CommandResult cres=output.getCommandResult();
//	
//	System.out.println(cres);
	
	for (DBObject c : output.results()){
		System.out.println(c.get("_id"));
	}
	
//	groupfield.put("","");
	}
}
