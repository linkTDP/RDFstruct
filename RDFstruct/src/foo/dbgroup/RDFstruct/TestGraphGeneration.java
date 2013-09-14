package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import foo.dbgroup.graphstream.BuildSchemaLodGraph;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;

public class TestGraphGeneration {
	
	public static void main(String[] args) {
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Morphia morphia = new Morphia();
		
		EndpointSparqlDAO dao = new EndpointSparqlDAO(mongo, morphia);
		
		EndPointSparql e=dao.findOne("_id", 1);
		
		BuildSchemaLodGraph a = new BuildSchemaLodGraph(e);
		
		a.generateGraph();
		
	}
	

}
