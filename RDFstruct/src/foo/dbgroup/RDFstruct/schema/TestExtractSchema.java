package foo.dbgroup.RDFstruct.schema;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;

public class TestExtractSchema {

	public static void main(String[] args) {
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		
		BuildVoidQuery qu=new BuildVoidQuery();

		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Morphia morphia = new Morphia();
		
		EndpointSparqlDAO dao = new EndpointSparqlDAO(mongo, morphia);
		//2 
		
//		EndPointSparql e=dao.findOne("_id", 1);
		List<EndPointSparql> repo=dao.find().asList();
		
//		System.out.println(repo.getLista().size());
		ExtractSchema exSchema=new ExtractSchema(mongo, morphia);
		
		for(EndPointSparql e:repo){
			if(e.getId()<1000){
				
				exSchema.setEndPoint(e);
				
				exSchema.extractAndSaveSchema();
			
			}
		}

	}

}
