package foo.dbgroup.RDFstruct;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import virtuoso.jena.driver.*;

public class TestConnection {
	
	public static void main( String[] args )
    {
		String url;
		if(args.length == 0)
		    url = "jdbc:virtuoso://localhost:1111/";
		else
		    url = args[0];

/*			STEP 1			*/
		VirtGraph set = new VirtGraph ("Wordnet3",url, "dba", "fagiolo");

//		Model md = VirtModel.openDatabaseModel("CountryCodeEurostat", url, "dba", "fagiolo");	
/*			STEP 2			*/


/*			STEP 3			*/
/*		Select all data in virtuoso	*/
		Query sparql = QueryFactory.create("SELECT (COUNT(DISTINCT ?o ) AS ?no) {  ?s ?p ?o  filter(!isLiteral(?o)) }    ");

/*			STEP 4			*/
		VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create (sparql, set);

		ResultSet results = vqe.execSelect();
		while (results.hasNext()) {
			QuerySolution result = results.nextSolution();
//		    RDFNode graph = result.get("graph");
		    RDFNode s = result.get("?o");
		    RDFNode p = result.get("?no");	
//		    RDFNode o = result.get("o");
		    System.out.println(s+"  ----  "+p);
//		    System.out.println(" { " + s + " " + p + " " + o + " . }");
		}
    }

}
