package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.datasource.EndpointRepository;
import foo.dbgroup.RDFstruct.datasource.VirtuosoLocalProvider;
import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExcecutorLocal;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;

public class TTTT {

	public static void main(String[] args) {
//		VirtuosoLocalProvider a = new VirtuosoLocalProvider("SnomedCT","dba","dba");
//		
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		
		BuildVoidQuery qu=new BuildVoidQuery();
		
		List<GenericQuery> liqu=qu.getVoidQuery();
		
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
		
		Datastore ds = morphia.createDatastore(mongo, "RDFstruct");
		
		
		QueryExecutorRemote exe = new QueryExecutorRemote();
		
		EndpointRepository repo= new EndpointRepository();
		
		for(EndPointSparql end:repo.getLista()){
			
			exe.setDataset(end.getUri(),end.getNome());
			
			
			for (GenericQuery current :liqu ){
				exe.setQuery(current);
				exe.executeQuery();
				exe.printResult();	
			}
			
			exe.saveToMongo(ds);
			
//			exe.printMarkDown(end.getNome());
		}
//		System.out.println(a.testConnetion());

	}

}
