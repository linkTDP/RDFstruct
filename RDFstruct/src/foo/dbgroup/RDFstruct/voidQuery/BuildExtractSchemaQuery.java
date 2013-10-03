package foo.dbgroup.RDFstruct.voidQuery;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.ParameterizedSparqlString;

import foo.dbgroup.mongo.entity.ClassLOD;
import foo.dbgroup.mongo.entity.GenericQuery;

public class BuildExtractSchemaQuery {

	public String start="SELECT DISTINCT ?Concept {[] a ?Concept}";
	public String secondLevelOld = "SELECT * { ?s a ?Concept.} LIMIT 10"; //IN futuro potrebbe essere utile anche IsUri  FILTER IsBLANK(?s) 
	public String secondLevel = "SELECT DISTINCT ?Popietry ?OClass {?SInstance a ?SClass. ?SInstance ?Popietry ?OInstance. ?OInstance a ?OClass.}";
	
	public GenericQuery start() {
		GenericQuery q=new GenericQuery();
		q.setAbsoluteQuery(start);
		q.setTitle("Get all the Classes");
		List<String> par = new ArrayList<String>();
		par.add("?Concept");
		q.setParameters(par);
		return q;
	}

@Deprecated
	public List<GenericQuery> secondLQueryOld(List<ClassLOD> clasLod) {
		List<GenericQuery> secLev=new ArrayList<GenericQuery>();
		for( ClassLOD current : clasLod){
			ParameterizedSparqlString queryStr = new ParameterizedSparqlString(secondLevelOld);
			queryStr.setIri("Concept", current.getClas());
			List<String> par=new ArrayList<String>();
			par.add("?s");
			GenericQuery currentQuery=new GenericQuery();
			currentQuery.setAbsoluteQuery(queryStr.toString());
			currentQuery.setParameters(par);
			currentQuery.setTitle("Second level query for the Class: "+current.getClas());
			currentQuery.setConstant(current.getClas());
			secLev.add(currentQuery);
			
		}
		return secLev;
	}


	public List<GenericQuery> secondLQuery(List<ClassLOD> clasLod){
		List<GenericQuery> secLev=new ArrayList<GenericQuery>();
		for( ClassLOD current : clasLod){
			if(!current.getClas().contains("http://www.openlinksw.com/schemas/virtrdf")){
				ParameterizedSparqlString queryStr = new ParameterizedSparqlString(secondLevel);
				queryStr.setIri("SClass", current.getClas());
				List<String> par=new ArrayList<String>();
				par.add("?Popietry");
				par.add("?OClass");
				GenericQuery currentQuery=new GenericQuery();
				currentQuery.setAbsoluteQuery(queryStr.toString());
				currentQuery.setParameters(par);
				currentQuery.setTitle("Second level query for the Class: "+current.getClas());
				currentQuery.setConstant(current.getClas());
				secLev.add(currentQuery);
			}
		}
		return secLev;
	}
	
}
