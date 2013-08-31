package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;

public class ResultFromSelectedEndpoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuildVoidQuery qu=new BuildVoidQuery();
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		List<GenericQuery> liqu=qu.getVoidQuery();
		
		QueryExecutorRemote exe = new QueryExecutorRemote();
		
		MongoClient mongoClient;
		
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			mongoClient = new MongoClient( "localhost" );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		
		EndpointSparqlDAO dao = new EndpointSparqlDAO(mongo, morphia);
		
		//*******************************************
		
		EndPointSparql e=dao.findOne("id", 15);
		
		exe.setDataset(e.getUri(),e.getNome());
		
		
		
		Datastore ds = morphia.createDatastore(mongo, "RDFstruct");
		
		for (GenericQuery current :liqu ){
			exe.setQuery(current);
			exe.executeQuery();
			exe.printResult();
			
		}
		exe.saveToMongo(ds);
	}

}