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
		//education Uk
		lista.add(new EndPointSparql("http://services.data.gov.uk/education/sparql","eduUK",2));
		//A Short Biographical Dictionary of English Literature
		lista.add(new EndPointSparql("http://biolit.rkbexplorer.com/sparql","A Short Biographical Dictionary of English Literature",3));
		//AEMET metereological dataset
		lista.add(new EndPointSparql("http://aemet.linkeddata.es/sparql","AEMET metereological dataset",4));
		//Accommodations in Tuscan
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/grrt","Accommodations in Tuscan",5));
		//Accommodations in Piedmont
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/grrp","Accommodations in Piedmont",6));
		//Archives Hub Linked Data
		lista.add(new EndPointSparql("http://data.archiveshub.ac.uk/sparql","Archives Hub Linked Data",7));
		//Bio2RDF - Drugbank
		lista.add(new EndPointSparql("http://aemet.linkeddata.es/sparql","Bio2RDF_Drugbank",8));		
		//Biographical Directory of the United States Congress
		lista.add(new EndPointSparql("http://logd.tw.rpi.edu/sparql","Biographical Directory of the United States Congress",9));
		//British Museum Collection
		lista.add(new EndPointSparql("http://collection.britishmuseum.org/Sparql","British Museum Collection",10));
		//British National Bibliography (BNB) - Linked Open Data
		lista.add(new EndPointSparql("http://bnb.data.bl.uk/sparql","British National Bibliography",11));
		//CTIC Public Dataset Catalogs
		lista.add(new EndPointSparql("http://data.fundacionctic.org/sparql","CTIC Public Dataset Catalogs",12));
		//DBLP in RDF (L3S)
		lista.add(new EndPointSparql("http://dblp.l3s.de/d2r/sparql","DBLP",13));
		//Data.gov
		lista.add(new EndPointSparql("http://services.data.gov/sparql","Data_gov",14));
		//EEA Vocabularies
		lista.add(new EndPointSparql("http://cr.eionet.europa.eu/sparql","EEA Vocabularies",15));
		//Enipedia - Energy Industry Data
		lista.add(new EndPointSparql("http://enipedia.tudelft.nl/sparql","Enipedia Energy Industry Data",16));
		//EventMedia
		lista.add(new EndPointSparql("http://eventmedia.eurecom.fr/sparql","EventMedia",17));
		//Fact Forge
		lista.add(new EndPointSparql("http://factforge.net/sparql","Fact Forge",18));
		//GeoLinkedData
		lista.add(new EndPointSparql("http://geo.linkeddata.es/sparql","GeoLinkedData",19));
		//GeoSpecies Knowledge Base
		lista.add(new EndPointSparql("http://lod.openlinksw.com/sparql","GeoSpecies Knowledge Base",20));
		//Geological Survey of Austria (GBA) - Thesaurus
		lista.add(new EndPointSparql("http://resource.geolba.ac.at/PoolParty/sparql/lithology","Geological Survey of Austria Thesaurus",21));
		//HealthData.gov Platform (HDP) on the Semantic Web
		lista.add(new EndPointSparql("http://healthdata.tw.rpi.edu/sparql","HealthData.gov Platform on the Semantic Web",22));
		//Italian Museums
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/musei","Italian Museums",23));
		//Italian public schools (LinkedOpenData.it)
		lista.add(new EndPointSparql("http://sparql.linkedopendata.it/scuole","Italian public schools",24));
		//LemonWordNet
		lista.add(new EndPointSparql("http://monnetproject.deri.ie/lemonsource_query/","LemonWordNet",25));
		//Linked Life Data
		lista.add(new EndPointSparql("http://linkedlifedata.com/sparql","Linked Life Data",26));
		//Linked Movie DataBase
		lista.add(new EndPointSparql("http://data.linkedmdb.org/sparql","Linked Movie DataBase",27));
		//Linked Open Data Camera dei deputati
		lista.add(new EndPointSparql("http://dati.camera.it/sparql","Linked Open Data Camera dei deputati",28));
		//Linked Open Vocabularies (LOV)
		lista.add(new EndPointSparql("http://lov.okfn.org/endpoint/lov","Linked Open Vocabularies",29));
		//LinkedCT
		lista.add(new EndPointSparql("http://data.linkedct.org/sparql","LinkedCT",30));
		//Open Data Thesaurus
		lista.add(new EndPointSparql("http://vocabulary.semantic-web.at/PoolParty/sparql/OpenData","Open Data Thesaurus",31));
		//PubMed
		lista.add(new EndPointSparql("http://pubmed.bio2rdf.org/sparql","PubMed",32));
		//RxNorm
		lista.add(new EndPointSparql("http://link.informatics.stonybrook.edu/sparql/","RxNorm",33));
		//SPARQL Endpoint Status
		lista.add(new EndPointSparql("http://labs.mondeca.com/endpoint/ends","SPARQL Endpoint Status",34));
		//STW Thesaurus for Economics
		lista.add(new EndPointSparql("http://zbw.eu/beta/sparql","STW Thesaurus for Economics",35));
		//Social Semantic Web Thesaurus
		lista.add(new EndPointSparql("http://vocabulary.semantic-web.at/PoolParty/sparql/semweb","Social Semantic Web Thesaurus",36));
		//Thesaurus W for Local Archives
		lista.add(new EndPointSparql("http://www.archivesdefrance.culture.gouv.fr/thesaurus/sparql","Thesaurus W for Local Archives",37));
		//UNESCO Nomenclature for fields of science and technology
		lista.add(new EndPointSparql("http://skos.um.es/sparql","UNESCO Nomenclature for fields of science and technology",38));
		//WordNet (RKBExplorer)
		lista.add(new EndPointSparql("http://wordnet.rkbexplorer.com/sparql/","WordNet",39));
		//wiktionary.dbpedia.org
		lista.add(new EndPointSparql("http://wiktionary.dbpedia.org/sparql","wiktionary_dbpedia_org",40));
		
		
		
	}

	public List<EndPointSparql> getLista() {
		return lista;
	}

	public void setLista(List<EndPointSparql> lista) {
		this.lista = lista;
	}
	
	
	
	
}
