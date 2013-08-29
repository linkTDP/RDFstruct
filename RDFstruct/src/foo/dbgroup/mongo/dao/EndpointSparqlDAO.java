package foo.dbgroup.mongo.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.entity.EndPointSparql;

public class EndpointSparqlDAO extends BasicDAO<EndPointSparql,String> {

	public EndpointSparqlDAO(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "RDFstruct");
		
	}

}
