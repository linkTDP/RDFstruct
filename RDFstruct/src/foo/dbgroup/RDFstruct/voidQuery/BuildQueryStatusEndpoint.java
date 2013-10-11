package foo.dbgroup.RDFstruct.voidQuery;

import java.util.ArrayList;
import java.util.List;

import foo.dbgroup.mongo.entity.GenericQuery;

public class BuildQueryStatusEndpoint {

	public final String status=
"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "+
"PREFIX dcterms:<http://purl.org/dc/terms/> "+
"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "+
"PREFIX owl:<http://www.w3.org/2002/07/owl#> "+
"PREFIX ends:<http://labs.mondeca.com/vocab/endpointStatus#> "+
"PREFIX void:<http://rdfs.org/ns/void#> "+
"SELECT distinct ?endpoint ?title "+
"WHERE{ "+
"	?dataset void:sparqlEndpoint ?endpoint. "+
"?dataset dcterms:title ?title. "+	
"} ";
	public final String title="Extract dataset from Status EndPoint";
	private final List<String> paramStatus=new ArrayList<String>(){{
		add("?endpoint");
		add("?title");
	}
	};
	
	private GenericQuery query;
	
	public BuildQueryStatusEndpoint(){
		setQuery(new GenericQuery());
		getQuery().setAbsoluteQuery(status);
		getQuery().setParameters(paramStatus);
		getQuery().setTitle(title);
	}

	public GenericQuery getQuery() {
		return query;
	}

	public void setQuery(GenericQuery query) {
		this.query = query;
	}
	
}
