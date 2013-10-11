package foo.dbgroup.RDFstruct.loadingScript;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.datasource.EndpointRepository;
import foo.dbgroup.RDFstruct.voidQuery.BuildNCBOquery;
import foo.dbgroup.RDFstruct.voidQuery.BuildQueryStatusEndpoint;
import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemoteNCBO;
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
		
		EndpointSparqlDAO endDao=new EndpointSparqlDAO( mongo,morphia);
		
		EndpointRepository Void=new EndpointRepository();
		
		for(EndPointSparql cur: Void.getLista()){
			
			if(endDao.findOne("uri",cur.getUri())==null)
				endDao.save(cur);
		}
		
		//find max Id
		
		List<EndPointSparql> endpoint=endDao.find().asList();
		int max=0;
		for(EndPointSparql cu : endpoint){
			if(cu.getId()>max)max=cu.getId();
		}
		max++;
		
		// get endpoint from remote
		
		BuildQueryStatusEndpoint query = new BuildQueryStatusEndpoint();
		
		QueryExecutorRemote exe = new QueryExecutorRemote();
		
		exe.setDataset(Void.getLista().get(2).getUri(), Void.getLista().get(2).getNome());
		
		exe.setQuery(query.getQuery());
		
		List<EndPointSparql> lista=exe.getEndPointFromStatus();
		
		for(EndPointSparql cur : lista){
			if(endDao.findOne("uri",cur.getUri())==null){
				cur.setId(max);
				endDao.save(cur);
				max++;
			}
		}
		
		// add the NCBO endpoint
		
		QueryExecutorRemoteNCBO exeN = new QueryExecutorRemoteNCBO();
		
		BuildNCBOquery queryN=new BuildNCBOquery();
		
//		exe.setDataset(query.getEndNCBO().getUri(), query.getEndNCBO().getNome());
		
		exeN.setQuery(queryN.getGetDataset());
		
		lista=exeN.getEndPointNCBO();
		
		max=1000;
		
		for(EndPointSparql current : lista){
			
			if(endDao.findOne("uri", current.getUri())==null){
				current.setId(max);
				max++;
				endDao.save(current);
			}
			
		}
		
		

	}

}
