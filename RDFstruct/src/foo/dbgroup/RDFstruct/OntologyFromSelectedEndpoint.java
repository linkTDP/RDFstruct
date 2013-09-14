package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import foo.dbgroup.RDFstruct.voidQuery.BuildExtractOntoQuery;
import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemoteNCBO;
import foo.dbgroup.mongo.dao.DatasetResultDAO;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.dao.MyTripleDAO;
import foo.dbgroup.mongo.dao.OwlStatementDAO;
import foo.dbgroup.mongo.dao.RdfsStatementDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.MyTriple;
import foo.dbgroup.mongo.entity.OwlStatement;
import foo.dbgroup.mongo.entity.RdfsStatement;

public class OntologyFromSelectedEndpoint {

	public static void main(String[] args) {
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		BuildVoidQuery qu=new BuildVoidQuery();
		BuildExtractOntoQuery quOn=new BuildExtractOntoQuery();
		
		
		//*******************************************
		
//		List<GenericQuery> liqu=qu.getVoidQuery();
		List<GenericQuery> liqu=quOn.getQuery();
		
		QueryExecutorRemote exe = new QueryExecutorRemote();
		
		
		
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Morphia morphia = new Morphia();
		
		EndpointSparqlDAO dao = new EndpointSparqlDAO(mongo, morphia);
		DatasetResultDAO rDao=new DatasetResultDAO(mongo, morphia);
		OwlStatementDAO oDao = new OwlStatementDAO(mongo,morphia);
		RdfsStatementDAO rdfsDao= new RdfsStatementDAO(mongo, morphia);
		MyTripleDAO triDao=new MyTripleDAO(mongo, morphia);
		//*******************************************
		
		EndPointSparql e=dao.findOne("_id", 1);
		
		exe.setDataset(e.getUri(),e.getNome());
		
		System.out.println(e.getUri());
		
		Datastore ds = morphia.createDatastore(mongo, "RDFstruct");
		
		List<MyTriple> myT=new ArrayList<MyTriple>();
		
		for (GenericQuery current :liqu ){
			exe.setQuery(current);
			exe.executeQuery();
			myT.addAll(exe.getTriple());	
		}
		
		for (MyTriple current : myT){
			//owl
				if(oDao.findOne("_id", current.getSubject())==null
						&&current.getSubject().contains("owl")){
					oDao.save(new OwlStatement(current.getSubject()));
				}
				if(oDao.findOne("_id", current.getPredicate())==null
						&&current.getPredicate().contains("owl")){
					oDao.save(new OwlStatement(current.getPredicate(),true));
				}
				if(oDao.findOne("_id", current.getObject())==null
						&&current.getObject().contains("owl")){
					oDao.save(new OwlStatement(current.getObject()));
				}
			//rdfs
				if(rdfsDao.findOne("_id", current.getSubject())==null
						&&current.getSubject().contains("rdf-schema#")){
					rdfsDao.save(new RdfsStatement(current.getSubject()));
				}
				if(rdfsDao.findOne("_id", current.getPredicate())==null
						&&current.getPredicate().contains("rdf-schema#")){
					rdfsDao.save(new RdfsStatement(current.getPredicate(),true));
				}
				if(rdfsDao.findOne("_id", current.getObject())==null
						&&current.getObject().contains("rdf-schema#")){
					rdfsDao.save(new RdfsStatement(current.getObject()));
				}
			if(!current.getSubject().contains("owl")&&!current.getSubject().contains("rdf-schema#")||
					!current.getPredicate().contains("owl")&&!current.getPredicate().contains("rdf-schema#")||
					!current.getObject().contains("owl")&&!current.getSubject().contains("rdf-schema#")){
				System.out.println(current.getSubject());
				System.out.println(current.getPredicate());
				System.out.println(current.getObject());
				System.out.println();
			}
			
			Query<MyTriple> queryTriple= ds.createQuery(MyTriple.class);
			queryTriple.field("subject").equal(current.getSubject());
			queryTriple.field("predicate").equal(current.getPredicate());
			queryTriple.field("object").equal(current.getObject());
			
			
			if(triDao.find(queryTriple)!=null){
				triDao.save(current);
			}
//			if(!current.getSubject().contains("owl")&&!current.getSubject().contains("rdf-schema#"))System.out.println(current.getSubject());
//			if(!current.getPredicate().contains("owl")&&!current.getPredicate().contains("rdf-schema#"))System.out.println(current.getPredicate());
//			if(!current.getObject().contains("owl")&&!current.getSubject().contains("rdf-schema#"))System.out.println(current.getObject());
		
		}
//		"http://www.w3.org/2000/01/rdf-schema#subClassOf"
	}

}
