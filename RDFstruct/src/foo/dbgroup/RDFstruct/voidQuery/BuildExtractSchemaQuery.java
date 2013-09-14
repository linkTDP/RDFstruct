package foo.dbgroup.RDFstruct.voidQuery;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.ParameterizedSparqlString;

import foo.dbgroup.mongo.entity.ClassLOD;
import foo.dbgroup.mongo.entity.GenericQuery;

public class BuildExtractSchemaQuery {

	public String start="SELECT DISTINCT ?Concept {[] a ?Concept}";
	public String secondLevel = "SELECT * { ?s a ?Concept. FILTER IsBLANK(?s) } LIMIT 10"; //IN futuro potrebbe essere utile anche IsUri
	
	
	public GenericQuery start() {
		GenericQuery q=new GenericQuery();
		q.setAbsoluteQuery(start);
		q.setTitle("Get all the Classes");
		List<String> par = new ArrayList<String>();
		par.add("?Concept");
		q.setParameters(par);
		return q;
	}


	public List<GenericQuery> secondLQuery(List<ClassLOD> clasLod) {
		List<GenericQuery> secLev=new ArrayList<GenericQuery>();
		for( ClassLOD current : clasLod){
			ParameterizedSparqlString queryStr = new ParameterizedSparqlString(secondLevel);
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

	
	
}
