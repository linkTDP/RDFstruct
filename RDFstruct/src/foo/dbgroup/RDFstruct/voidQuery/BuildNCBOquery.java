package foo.dbgroup.RDFstruct.voidQuery;

import java.util.ArrayList;
import java.util.List;

import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;

public class BuildNCBOquery {

	private GenericQuery getDataset;
	
	private EndPointSparql endNCBO;

	public BuildNCBOquery() {
		super();
		StringBuilder query=new StringBuilder();
		query.append("PREFIX meta: <http://bioportal.bioontology.org/metadata/def/> ");
		query.append("PREFIX omv: <http://omv.ontoware.org/2005/05/ontology#> ");
		query.append("SELECT DISTINCT ?ont ?graph ?acr ?name ");
		query.append("WHERE { ?ont meta:hasDataGraph ?graph . ");
		query.append("?ont a omv:Ontology . ");
		query.append("?ont omv:acronym ?acr . ");
		query.append("?ont omv:name ?name . }");
		setGetDataset(new GenericQuery());
		getGetDataset().setAbsoluteQuery(query.toString());
		getGetDataset().setTitle("Get al the dataset from NCBO");
		List<String> params=new ArrayList<String>();
		params.add("?ont");
		params.add("?graph");
		params.add("?acr");
		params.add("?name");
		getGetDataset().setParameters(params);
		
		setEndNCBO(new EndPointSparql());
		getEndNCBO().setUri("http://sparql.bioontology.org/");
		getEndNCBO().setNome("NCBO portal");
	}

	public GenericQuery getGetDataset() {
		return getDataset;
	}

	public void setGetDataset(GenericQuery getDataset) {
		this.getDataset = getDataset;
	}

	public EndPointSparql getEndNCBO() {
		return endNCBO;
	}

	public void setEndNCBO(EndPointSparql endNCBO) {
		this.endNCBO = endNCBO;
	}

	
//	private final String void1="SELECT (COUNT(*) AS ?no) { ?s ?p ?o  }";
//	private final String tvoid1="total number of triples";
//	private final List<String> pvoid1=new ArrayList<String>(){{
//		add("?no");
//	}
//	};
	
	
	
	
	
}
