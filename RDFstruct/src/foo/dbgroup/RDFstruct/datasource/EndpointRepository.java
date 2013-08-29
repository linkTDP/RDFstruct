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
		lista.add(new EndPointSparql("http://dati.senato.it/sparql","senato"));
		//education Uk
		lista.add(new EndPointSparql("http://services.data.gov.uk/education/sparql","eduUK"));
		//A Short Biographical Dictionary of English Literature
		lista.add(new EndPointSparql("http://biolit.rkbexplorer.com/sparql","A Short Biographical Dictionary of English Literature"));
		//AEMET metereological dataset
		lista.add(new EndPointSparql("http://aemet.linkeddata.es/sparql","AEMET metereological dataset"));
		//Accommodations in Tuscan
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/grrt","Accommodations in Tuscan"));
		//Accommodations in Piedmont
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/grrp","Accommodations in Piedmont"));
		//Archives Hub Linked Data
		lista.add(new EndPointSparql("http://data.archiveshub.ac.uk/sparql","Archives Hub Linked Data"));
		//Bio2RDF - Drugbank
		lista.add(new EndPointSparql("http://aemet.linkeddata.es/sparql","Bio2RDF_Drugbank"));		
		//Biographical Directory of the United States Congress
		lista.add(new EndPointSparql("http://logd.tw.rpi.edu/sparql","Biographical Directory of the United States Congress"));
		//British Museum Collection
		lista.add(new EndPointSparql("http://collection.britishmuseum.org/Sparql","British Museum Collection"));
		//British National Bibliography (BNB) - Linked Open Data
		lista.add(new EndPointSparql("http://bnb.data.bl.uk/sparql","British National Bibliography"));
		//CTIC Public Dataset Catalogs
		lista.add(new EndPointSparql("http://data.fundacionctic.org/sparql","CTIC Public Dataset Catalogs"));
		//DBLP in RDF (L3S)
		lista.add(new EndPointSparql("http://dblp.l3s.de/d2r/sparql","DBLP"));
		//Data.gov
		lista.add(new EndPointSparql("http://services.data.gov/sparql","Data_gov"));
		//EEA Vocabularies
		lista.add(new EndPointSparql("http://cr.eionet.europa.eu/sparql","EEA Vocabularies"));
		//Enipedia - Energy Industry Data
		lista.add(new EndPointSparql("http://enipedia.tudelft.nl/sparql","Enipedia Energy Industry Data"));
		//EventMedia
		lista.add(new EndPointSparql("http://eventmedia.eurecom.fr/sparql","EventMedia"));
		//Fact Forge
		lista.add(new EndPointSparql("http://factforge.net/sparql","Fact Forge"));
		//GeoLinkedData
		lista.add(new EndPointSparql("http://geo.linkeddata.es/sparql","GeoLinkedData"));
		//GeoSpecies Knowledge Base
		lista.add(new EndPointSparql("http://lod.openlinksw.com/sparql","GeoSpecies Knowledge Base"));
		//Geological Survey of Austria (GBA) - Thesaurus
		lista.add(new EndPointSparql("http://resource.geolba.ac.at/PoolParty/sparql/lithology","Geological Survey of Austria Thesaurus"));
		//HealthData.gov Platform (HDP) on the Semantic Web
		lista.add(new EndPointSparql("http://healthdata.tw.rpi.edu/sparql","HealthData.gov Platform on the Semantic Web"));
		//Italian Museums
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/musei","Italian Museums"));
		//Italian public schools (LinkedOpenData.it)
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/scuole","Italian public schools"));
		//LemonWordNet
		lista.add(new EndPointSparql("http://monnetproject.deri.ie/lemonsource_query/","LemonWordNet"));
		//Linked Life Data
		lista.add(new EndPointSparql("http://linkedlifedata.com/sparql","Linked Life Data"));
		//Linked Movie DataBase
		lista.add(new EndPointSparql("http://data.linkedmdb.org/sparql","Linked Movie DataBase"));
		//Linked Open Data Camera dei deputati
		lista.add(new EndPointSparql("http://dati.camera.it/sparql","Linked Open Data Camera dei deputati"));
		//Linked Open Vocabularies (LOV)
		lista.add(new EndPointSparql("http://lov.okfn.org/endpoint/lov","Linked Open Vocabularies"));
		//LinkedCT
		lista.add(new EndPointSparql("http://data.linkedct.org/sparql","LinkedCT"));
		//Open Data Thesaurus
		lista.add(new EndPointSparql("http://vocabulary.semantic-web.at/PoolParty/sparql/OpenData","Open Data Thesaurus"));
		//PubMed
		lista.add(new EndPointSparql("http://pubmed.bio2rdf.org/sparql","PubMed"));
		//RxNorm
		lista.add(new EndPointSparql("http://link.informatics.stonybrook.edu/sparql/","RxNorm"));
		//SPARQL Endpoint Status
		lista.add(new EndPointSparql("http://labs.mondeca.com/endpoint/ends","SPARQL Endpoint Status"));
		//STW Thesaurus for Economics
		lista.add(new EndPointSparql("http://zbw.eu/beta/sparql","STW Thesaurus for Economics"));
		//Social Semantic Web Thesaurus
		lista.add(new EndPointSparql("http://vocabulary.semantic-web.at/PoolParty/sparql/semweb","Social Semantic Web Thesaurus"));
		//Thesaurus W for Local Archives
		lista.add(new EndPointSparql("http://www.archivesdefrance.culture.gouv.fr/thesaurus/sparql","Thesaurus W for Local Archives"));
		//UNESCO Nomenclature for fields of science and technology
		lista.add(new EndPointSparql("http://skos.um.es/sparql","UNESCO Nomenclature for fields of science and technology"));
		//WordNet (RKBExplorer)
		lista.add(new EndPointSparql("http://wordnet.rkbexplorer.com/sparql/","WordNet"));
		//wiktionary.dbpedia.org
		lista.add(new EndPointSparql("http://wiktionary.dbpedia.org/sparql","wiktionary_dbpedia_org"));
		
		
		
	}

	public List<EndPointSparql> getLista() {
		return lista;
	}

	public void setLista(List<EndPointSparql> lista) {
		this.lista = lista;
	}
	
	
	
	
}
