package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Pattern;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.voidQuery.BuildExtractOntoQuery;
import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.mongo.dao.DatasetResultDAO;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.dao.MyTripleDAO;
import foo.dbgroup.mongo.dao.OwlStatementDAO;
import foo.dbgroup.mongo.dao.RdfsStatementDAO;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.MyTriple;

public class TestAnalisysTriple {

	public static void main(String[] args) {
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		
		
		
		
		//*******************************************
		
		
		
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
		
		Datastore ds = morphia.createDatastore(mongo, "RDFstruct");
		
		
		
		Pattern regexpOwl = Pattern.compile("owl");
		Pattern regexpRdfs = Pattern.compile("rdf-schema");
		
		Criteria sOwl=triDao.createQuery().criteria("subject").contains("www.w3.org/2002/07/owl#Class");
		Criteria pOwl=triDao.createQuery().criteria("predicate").contains("www.w3.org/2002/07/owl#Class");
		Criteria oOwl=triDao.createQuery().criteria("object").contains("www.w3.org/2002/07/owl#Class");
		Criteria sRdfs=triDao.createQuery().criteria("subject").contains("rdf-schema#Class");
		Criteria pRdfs=triDao.createQuery().criteria("predicate").contains("rdf-schema#Class");
		Criteria oRdfs=triDao.createQuery().criteria("object").contains("rdf-schema#Class");
		
		Query<MyTriple> q=triDao.createQuery();
//		q.and(q.or(oOwl,oRdfs),q.or(pOwl,pRdfs));
		q.or(sOwl,oOwl,pOwl,sRdfs,oRdfs,pRdfs);
		List<MyTriple> res= triDao.find(q).asList();
		
		for(MyTriple cur: res)System.out.println(cur.toString());
		
		System.out.println(q.toString());
		

	}

}
