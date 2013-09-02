package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.voidQuery.BuildNCBOquery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemoteNCBO;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;

public class GetNCBODatasets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		Morphia morphia = new Morphia();
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EndpointSparqlDAO endDao = new EndpointSparqlDAO(mongo, morphia);
		
		QueryExecutorRemoteNCBO exe = new QueryExecutorRemoteNCBO();
		
		BuildNCBOquery query=new BuildNCBOquery();
		
//		exe.setDataset(query.getEndNCBO().getUri(), query.getEndNCBO().getNome());
		
		exe.setQuery(query.getGetDataset());
		
		exe.executeQuery(false);
		List<EndPointSparql> lista=exe.getEndPointNCBO();
		
		// indivifua il massimo id presente nei dataset
		
		List<EndPointSparql> endpoint=endDao.find().asList();
		int max=0;
		for(EndPointSparql cu : endpoint){
			if(cu.getId()>max)max=cu.getId();
		}
		max++;
		for(EndPointSparql current : lista){
			
			if(endDao.findOne("uri", current.getUri())==null){
				current.setId(max);
				max++;
				endDao.save(current);
			}
			
		}
		
		
	}

}
