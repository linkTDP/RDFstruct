package foo.dbgroup.RDFstruct.schema;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.graphstream.BuildSchemaLodGraph;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.dao.SchemaLODDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.SchemaLOD;

public class TestDrawGraphFromMongo {

	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		
		
		
		
		
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		
//		QueryExcecutorLocal exe=new QueryExcecutorLocal();
//		
//		exe.setDataset(a.getVgraph());
		Morphia morphia = new Morphia();
		
		SchemaLODDAO schemaDao=new SchemaLODDAO(mongo, morphia);
		
		EndpointSparqlDAO endDao= new EndpointSparqlDAO(mongo, morphia);
		
//		EndPointSparql end= endDao.findOne("_id", 52);
		
		List<SchemaLOD> schemi=schemaDao.getSchemaWithEdge();
		
		BuildSchemaLodGraph graph=new BuildSchemaLodGraph();
		
		graph.drowGraphFromMongo(schemi.get(15));

	}

}
