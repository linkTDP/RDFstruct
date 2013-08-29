package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.google.code.morphia.query.QueryResults;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.dao.GenericQueryDAO;
import foo.dbgroup.mongo.entity.GenericQuery;

public class EndpointTest {

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
		
		GenericQueryDAO queryDao=new GenericQueryDAO( mongo,morphia);
		
		
		
		List<GenericQuery> q=queryDao.find().asList();
		
		for(GenericQuery cur : q){
			System.out.println(cur.getQuery());
		}
	
	}

}
