package foo.dbgroup.RDFstruct.voidQuery;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.dao.OwlStatementDAO;
import foo.dbgroup.mongo.dao.RdfsStatementDAO;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.OwlStatement;
import foo.dbgroup.mongo.entity.RdfsStatement;

public class BuildExtractOntoQuery {
	
//	public String prefix="PREFIX owl: <http://www.w3.org/2002/07/owl#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>";
	public String prefix="";
	
	public String owlPref="http://www.w3.org/2002/07/owl#";
	public String bodyQuery=" SELECT * {?a ?b ?c} ";
	public String[] owl={"Thing","Nothing","Class","ObjectProperty" , "Restriction"};
	public String[] rdfs;
	private List<GenericQuery> query;
	OwlStatementDAO oDao ;
	RdfsStatementDAO rDao;
	
	public BuildExtractOntoQuery(){
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Morphia morphia = new Morphia();
		oDao = new OwlStatementDAO(mongo,morphia);
		rDao = new RdfsStatementDAO(mongo, morphia);
		
		List<OwlStatement> owls=oDao.find().asList();
		List<String> tmpOwl=null;
		if(owls!=null){
			tmpOwl=new ArrayList<String>();
			for(OwlStatement cur:owls)tmpOwl.add(cur.getStatement());
			owl=new String[tmpOwl.size()];
			int i=0;
			for(String cu:tmpOwl){
				owl[i]=cu;
				i++;
			}
		}else{
			for(int i=0; i< owl.length; i++){
				owl[i]=prefix+owl[i];
			}
		}
		
		//
		
		
		List<RdfsStatement> rdfss=rDao.find().asList();
		rdfs=new String[rdfss.size()];
		int l=0;
		for(RdfsStatement cu:rdfss){
			rdfs[l]=cu.getStatement();
			l++;
		}
		
		setQuery(new ArrayList<GenericQuery>());
		for (int i = 0; i< owl.length*3 ; i++){
			GenericQuery g = new GenericQuery();
			List<String> par;
			g.setNumber(i);
			StringBuilder builder=new StringBuilder();
			builder.append(prefix);
			builder.append(bodyQuery);
			
			g.setConstant(owl[i/3]);
			ParameterizedSparqlString queryStr = new ParameterizedSparqlString(builder.toString());
			switch(i%3){
				case 0:	queryStr.setIri("a",owl[i/3]);
						g.setAbsoluteQuery(queryStr.toString());
						par = new ArrayList<String>();
						par.add("?b");
						par.add("?c");
						g.setSubject(true);
						g.setParameters(par);
						break;
				case 1:	queryStr.setIri("b", owl[i/3]);
						g.setAbsoluteQuery(queryStr.toString());
						par = new ArrayList<String>();
						par.add("?a");
						par.add("?c");
						g.setPredicate(true);
						g.setParameters(par);
						break;
				case 2:	queryStr.setIri("c", owl[i/3]);
						g.setAbsoluteQuery(queryStr.toString());
						par = new ArrayList<String>();
						par.add("?a");
						par.add("?b");
						g.setObject(true);
						g.setParameters(par);
						break;
			}
			getQuery().add(g);
		}
		
		for (int i = 0; i< rdfs.length*3 ; i++){
			GenericQuery g = new GenericQuery();
			List<String> par;
			g.setNumber(i);
			StringBuilder builder=new StringBuilder();
			builder.append(prefix);
			builder.append(bodyQuery);
			
			g.setConstant(rdfs[i/3]);
			ParameterizedSparqlString queryStr = new ParameterizedSparqlString(builder.toString());
			switch(i%3){
				case 0:	queryStr.setIri("a",rdfs[i/3]);
						g.setAbsoluteQuery(queryStr.toString());
						par = new ArrayList<String>();
						par.add("?b");
						par.add("?c");
						g.setSubject(true);
						g.setParameters(par);
						break;
				case 1:	queryStr.setIri("b", rdfs[i/3]);
						g.setAbsoluteQuery(queryStr.toString());
						par = new ArrayList<String>();
						par.add("?a");
						par.add("?c");
						g.setPredicate(true);
						g.setParameters(par);
						break;
				case 2:	queryStr.setIri("c", rdfs[i/3]);
						g.setAbsoluteQuery(queryStr.toString());
						par = new ArrayList<String>();
						par.add("?a");
						par.add("?b");
						g.setObject(true);
						g.setParameters(par);
						break;
			}
			getQuery().add(g);
		}
		
	}

	public List<GenericQuery> getQuery() {
		return query;
	}

	public void setQuery(List<GenericQuery> query) {
		this.query = query;
	}
	
	
}
