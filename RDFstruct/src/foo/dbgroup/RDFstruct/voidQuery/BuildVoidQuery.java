package foo.dbgroup.RDFstruct.voidQuery;

import java.util.ArrayList;
import java.util.List;

import foo.dbgroup.mongo.entity.GenericQuery;

public class BuildVoidQuery {
	
	List<GenericQuery> voidQuery;

	private final String void1="SELECT (COUNT(*) AS ?no) { ?s ?p ?o  }";
	private final String tvoid1="total number of triples";
	private final List<String> pvoid1=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	
	private final String void2="SELECT (COUNT(distinct ?s) AS ?no) { ?s a []  }";
	private final String tvoid2="total number of entities";
	private final List<String> pvoid2=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String void3="SELECT (COUNT(DISTINCT ?s ) AS ?no) { { ?s ?p ?o  } UNION { ?o ?p ?s } FILTER(!isBlank(?s) && !isLiteral(?s)) }    ";
	private final String tvoid3="total number of distinct resource URIs (deprecated??)";
	private final List<String> pvoid3=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String void4="SELECT (COUNT(distinct ?o) AS ?no) { ?s rdf:type ?o }";
	private final String tvoid4="total number of distinct classes";
	private final List<String> pvoid4=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String void5="SELECT (count(distinct ?p) AS ?no) { ?s ?p ?o }";
	private final String tvoid5="total number of distinct predicates";
	private final List<String> pvoid5=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String void6="SELECT (COUNT(DISTINCT ?s ) AS ?no) {  ?s ?p ?o   } ";
	private final String tvoid6="total number of distinct subject nodes";
	private final List<String> pvoid6=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String void7="SELECT (COUNT(DISTINCT ?o ) AS ?no) {  ?s ?p ?o  filter(!isLiteral(?o)) }    ";
	private final String tvoid7="total number of distinct object nodes";
	private final List<String> pvoid7=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String void8="SELECT DISTINCT ?list { ?s a ?list }";
	private final String tvoid8="exhaustive list of classes used in the dataset";
	private final List<String> pvoid8=new ArrayList<String>(){{
		add("?list");
	}
	};
	
	private final String void9="SELECT DISTINCT ?list { ?s ?list ?o }";
	private final String tvoid9="exhaustive list of properties used in the dataset";
	private final List<String> pvoid9=new ArrayList<String>(){{
		add("?list");
	}
	};
	
	private final String void10="SELECT  ?class (COUNT(?s) AS ?count ) { ?s a ?class } GROUP BY ?class ORDER BY ?count";
	private final String tvoid10="table: class vs. total number of instances of the class";
	private final List<String> pvoid10=new ArrayList<String>(){{
		add("?class");
		add("?count");
	}
	};
	
	private final String void11="SELECT  ?p (COUNT(?s) AS ?count ) { ?s ?p ?o } GROUP BY ?p ORDER BY ?count";
	private final String tvoid11="table: property vs. total number of triples using the property";
	private final List<String> pvoid11=new ArrayList<String>(){{
		add("?p");
		add("?count");
	}
	};
	
	private final String void12="SELECT  ?p (COUNT(DISTINCT ?s ) AS ?count ) { ?s ?p ?o } GROUP BY ?p ORDER BY ?count";
	private final String tvoid12="table: property vs. total number of distinct subjects in triples using the property";
	private final List<String> pvoid12=new ArrayList<String>(){{
		add("?p");
		add("?count");
	}
	};
	
	private final String void13="SELECT  ?p (COUNT(DISTINCT ?o ) AS ?count ) { ?s ?p ?o } GROUP BY ?p ORDER BY ?count";
	private final String tvoid13="table: property vs. total number of distinct objects in triples using the property";
	private final List<String> pvoid13=new ArrayList<String>(){{
		add("?p");
		add("?count");
	}
	};
	
	private final String void14="SELECT DISTINCT ?list WHERE  {{?uri ?p ?o .} UNION {?sub ?p ?uri .FILTER(isIRI(?uri))}BIND (str(?uri) as ?s)FILTER (STRSTARTS(?s, \"http://\"))BIND (IRI(CONCAT(\"http://\", STRBEFORE(SUBSTR(?s, 8), \"/\"))) AS ?list)}";
	private final String tvoid14="list of all domain names occurring in URIs in subjects or objects";
	private final List<String> pvoid14=new ArrayList<String>(){{
		add("?list");
	}
	};
	
	
	public BuildVoidQuery() {
		super();
		voidQuery=new ArrayList<GenericQuery>();
		GenericQuery a = new GenericQuery();
		a.setParameters(pvoid1);
		a.setQuery(void1);
		a.setTitle(tvoid1);
		a.setNumber(1);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid2);
		a.setQuery(void2);
		a.setTitle(tvoid2);
		a.setNumber(2);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid3);
		a.setQuery(void3);
		a.setTitle(tvoid3);
		a.setNumber(3);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid4);
		a.setQuery(void4);
		a.setTitle(tvoid4);
		a.setNumber(4);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid5);
		a.setQuery(void5);
		a.setTitle(tvoid5);
		a.setNumber(5);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid6);
		a.setQuery(void6);
		a.setTitle(tvoid6);
		a.setNumber(6);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid7);
		a.setQuery(void7);
		a.setTitle(tvoid7);
		a.setNumber(7);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid8);
		a.setQuery(void8);
		a.setTitle(tvoid8);
		a.setNumber(8);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid9);
		a.setQuery(void9);
		a.setTitle(tvoid9);
		a.setNumber(9);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid10);
		a.setQuery(void10);
		a.setTitle(tvoid10);
		a.setNumber(10);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid11);
		a.setQuery(void11);
		a.setTitle(tvoid11);
		a.setNumber(11);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid12);
		a.setQuery(void12);
		a.setTitle(tvoid12);
		a.setNumber(12);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid13);
		a.setQuery(void13);
		a.setTitle(tvoid13);
		a.setNumber(13);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid14);
		a.setQuery(void14);
		a.setTitle(tvoid14);
		a.setNumber(14);
		voidQuery.add(a);
		
		
	}
	
	public List<GenericQuery> getVoidQuery(){
		return voidQuery;
	}
	
	
}
