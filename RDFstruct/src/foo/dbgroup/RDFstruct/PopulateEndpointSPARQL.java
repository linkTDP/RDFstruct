package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.datasource.EndpointRepository;
import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.dao.GenericQueryDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;

public class PopulateEndpointSPARQL {

	public static void main(String[] args) {
		
		
		
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		
		EndpointSparqlDAO queryDao=new EndpointSparqlDAO( mongo,morphia);
		
		EndpointRepository Void=new EndpointRepository();
		
		for(EndPointSparql cur: Void.getLista()){
			queryDao.save(cur);
		}
		
		

	}

}
