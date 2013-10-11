package foo.dbgroup.RDFstruct.datasource;

import java.util.ArrayList;
import java.util.List;

import foo.dbgroup.mongo.entity.EndPointSparql;

public class EndpointRepository {

	private List<EndPointSparql> lista;

	
	
	
	
	
	public EndpointRepository() {
		super();
		lista = new ArrayList<EndPointSparql>();
		//senato
		lista.add(new EndPointSparql("http://dati.senato.it/sparql","senato",1));
		//wiktionary.dbpedia.org
		lista.add(new EndPointSparql("http://wiktionary.dbpedia.org/sparql","wiktionary_dbpedia_org",2));
		//status endpoint
		lista.add(new EndPointSparql("http://labs.mondeca.com/endpoint/ends", "Endpoint Status", 3));
		
		
	}

	public List<EndPointSparql> getLista() {
		return lista;
	}

	public void setLista(List<EndPointSparql> lista) {
		this.lista = lista;
	}
	
	
	
	
}
