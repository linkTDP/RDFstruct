package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import foo.dbgroup.graphstream.BuildSchemaLodGraph;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;

public class TestGraphGeneration {
	
	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
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
		
		EndPointSparql e=dao.findOne("_id", 1);
		
		BuildSchemaLodGraph a = new BuildSchemaLodGraph();
		
		a.generateGraph(e);
		
	}
	

}
