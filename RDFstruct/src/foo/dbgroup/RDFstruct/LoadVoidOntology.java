package foo.dbgroup.RDFstruct;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolutionMap;

public class LoadVoidOntology {

	public static void main( String[] args )
    {
		String a = "12965190^^http://www.w3.org/2001/XMLSchema#integer";
		
		a.replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#integer", "");
		
		System.out.println(a.replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#integer", ""));
		
		System.out.println(3%3);
		
		int c= 0;
		
		int b= 3;
		
		System.out.println(c/b);
		
		String q="PREFIX owl: <http://www.w3.org/2002/07/owl#> SELECT ?a ?b ?c {?a ?b ?c}";
		
		Query sparql = QueryFactory.create(q);
		
		QuerySolutionMap initialBindings = new QuerySolutionMap();
		
		ParameterizedSparqlString queryStr = new ParameterizedSparqlString(q);
		
		queryStr.setIri("a", "http://www.w3.org/2002/07/owl#Thing");
		
		System.out.println(queryStr.toString());
		
		
		QueryExecution qe = QueryExecutionFactory.sparqlService("", queryStr.asQuery());
		
		
		
		
    }
}
