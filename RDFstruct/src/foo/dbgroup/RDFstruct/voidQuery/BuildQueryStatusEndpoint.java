package foo.dbgroup.RDFstruct.voidQuery;

public class BuildQueryStatusEndpoint {

	public String start=
"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>"+
"PREFIX dcterms:<http://purl.org/dc/terms/>"+
"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"+
"PREFIX owl:<http://www.w3.org/2002/07/owl#>"+
"PREFIX ends:<http://labs.mondeca.com/vocab/endpointStatus#>"+
"PREFIX void:<http://rdfs.org/ns/void#>"+
"SELECT distinct ?endpoint ?title"+
"WHERE{"+
"	?dataset void:sparqlEndpoint ?endpoint."+
"?dataset dcterms:title ?title."+	
"}";
	
	
}
