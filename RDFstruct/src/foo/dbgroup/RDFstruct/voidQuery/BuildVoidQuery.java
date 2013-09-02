package foo.dbgroup.RDFstruct.voidQuery;

import java.util.ArrayList;
import java.util.List;

import foo.dbgroup.mongo.entity.GenericQuery;

public class BuildVoidQuery {
	
	List<GenericQuery> voidQuery;

	private final String preVoid1="SELECT (COUNT(*) AS ?no) ";
	private final String postVoid1=" { ?s ?p ?o  } "; 
	private final String tvoid1="total number of triples";
	private final List<String> pvoid1=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	
	private final String preVoid2="SELECT (COUNT(distinct ?s) AS ?no) ";
	private final String postVoid2=" { ?s a []  } ";
	private final String tvoid2="total number of entities";
	private final List<String> pvoid2=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String preVoid3="SELECT (COUNT(DISTINCT ?s ) AS ?no) ";
	private final String postVoid3=" { { ?s ?p ?o  } UNION { ?o ?p ?s } FILTER(!isBlank(?s) && !isLiteral(?s)) }";
	private final String tvoid3="total number of distinct resource URIs (deprecated??)";
	private final List<String> pvoid3=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String preVoid4="SELECT (COUNT(distinct ?o) AS ?no) ";
	private final String postVoid4=" { ?s rdf:type ?o } ";
	private final String tvoid4="total number of distinct classes";
	private final List<String> pvoid4=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String preVoid5="SELECT (count(distinct ?p) AS ?no) ";
	private final String postVoid5=" { ?s ?p ?o }";
	private final String tvoid5="total number of distinct predicates";
	private final List<String> pvoid5=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String preVoid6="SELECT (COUNT(DISTINCT ?s ) AS ?no)  ";
	private final String postVoid6="{  ?s ?p ?o   }";
	private final String tvoid6="total number of distinct subject nodes";
	private final List<String> pvoid6=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String preVoid7="SELECT (COUNT(DISTINCT ?o ) AS ?no)    ";
	private final String postVoid7="{  ?s ?p ?o  filter(!isLiteral(?o)) } ";
	private final String tvoid7="total number of distinct object nodes";
	private final List<String> pvoid7=new ArrayList<String>(){{
		add("?no");
	}
	};
	
	private final String preVoid8="SELECT DISTINCT ?list ";
	private final String postVoid8=" { ?s a ?list }";
	private final String tvoid8="exhaustive list of classes used in the dataset";
	private final List<String> pvoid8=new ArrayList<String>(){{
		add("?list");
	}
	};
	
	private final String preVoid9="SELECT DISTINCT ?list ";
	private final String postVoid9=" { ?s ?list ?o }";
	private final String tvoid9="exhaustive list of properties used in the dataset";
	private final List<String> pvoid9=new ArrayList<String>(){{
		add("?list");
	}
	};
	
	private final String preVoid10="SELECT  ?class (COUNT(?s) AS ?count ) ";
	private final String postVoid10=" { ?s a ?class } GROUP BY ?class ORDER BY ?count";
	private final String tvoid10="table: class vs. total number of instances of the class";
	private final List<String> pvoid10=new ArrayList<String>(){{
		add("?class");
		add("?count");
	}
	};
	
	private final String preVoid11="SELECT  ?p (COUNT(?s) AS ?count ) ";
	private final String postVoid11=" { ?s ?p ?o } GROUP BY ?p ORDER BY ?count";
	private final String tvoid11="table: property vs. total number of triples using the property";
	private final List<String> pvoid11=new ArrayList<String>(){{
		add("?p");
		add("?count");
	}
	};
	
	private final String preVoid12="SELECT  ?p (COUNT(DISTINCT ?s ) AS ?count ) ";
	private final String postVoid12=" { ?s ?p ?o } GROUP BY ?p ORDER BY ?count";
	private final String tvoid12="table: property vs. total number of distinct subjects in triples using the property";
	private final List<String> pvoid12=new ArrayList<String>(){{
		add("?p");
		add("?count");
	}
	};
	
	private final String preVoid13="SELECT  ?p (COUNT(DISTINCT ?o ) AS ?count ) ";
	private final String postVoid13=" { ?s ?p ?o } GROUP BY ?p ORDER BY ?count";
	private final String tvoid13="table: property vs. total number of distinct objects in triples using the property";
	private final List<String> pvoid13=new ArrayList<String>(){{
		add("?p");
		add("?count");
	}
	};
	
	private final String preVoid14="SELECT DISTINCT ?list ";
	private final String postVoid14=" WHERE  {{?uri ?p ?o .} UNION {?sub ?p ?uri .FILTER(isIRI(?uri))}BIND (str(?uri) as ?s)FILTER (STRSTARTS(?s, \"http://\"))BIND (IRI(CONCAT(\"http://\", STRBEFORE(SUBSTR(?s, 8), \"/\"))) AS ?list)}";
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
		a.setPrequery(preVoid1);
		a.setPostquery(postVoid1);
		a.setTitle(tvoid1);
		a.setNumber(1);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid2);
		a.setPrequery(preVoid2);
		a.setPostquery(postVoid2);
		a.setTitle(tvoid2);
		a.setNumber(2);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid3);
		a.setPrequery(preVoid3);
		a.setPostquery(postVoid3);
		a.setTitle(tvoid3);
		a.setNumber(3);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid4);
		a.setPrequery(preVoid4);
		a.setPostquery(postVoid4);
		a.setTitle(tvoid4);
		a.setNumber(4);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid5);
		a.setPrequery(preVoid5);
		a.setPostquery(postVoid5);
		a.setTitle(tvoid5);
		a.setNumber(5);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid6);
		a.setPrequery(preVoid6);
		a.setPostquery(postVoid6);
		a.setTitle(tvoid6);
		a.setNumber(6);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid7);
		a.setPrequery(preVoid7);
		a.setPostquery(postVoid7);
		a.setTitle(tvoid7);
		a.setNumber(7);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid8);
		a.setPrequery(preVoid8);
		a.setPostquery(postVoid8);
		a.setTitle(tvoid8);
		a.setNumber(8);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid9);
		a.setPrequery(preVoid9);
		a.setPostquery(postVoid9);
		a.setTitle(tvoid9);
		a.setNumber(9);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid10);
		a.setPrequery(preVoid10);
		a.setPostquery(postVoid10);
		a.setTitle(tvoid10);
		a.setNumber(10);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid11);
		a.setPrequery(preVoid11);
		a.setPostquery(postVoid11);
		a.setTitle(tvoid11);
		a.setNumber(11);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid12);
		a.setPrequery(preVoid12);
		a.setPostquery(postVoid12);
		a.setTitle(tvoid12);
		a.setNumber(12);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid13);
		a.setPrequery(preVoid13);
		a.setPostquery(postVoid13);
		a.setTitle(tvoid13);
		a.setNumber(13);
		voidQuery.add(a);
		
		a = new GenericQuery();
		a.setParameters(pvoid14);
		a.setPrequery(preVoid14);
		a.setPostquery(postVoid14);
		a.setTitle(tvoid14);
		a.setNumber(14);
		voidQuery.add(a);
		
		
	}
	
	public List<GenericQuery> getVoidQuery(){
		return voidQuery;
	}
	
	
}
