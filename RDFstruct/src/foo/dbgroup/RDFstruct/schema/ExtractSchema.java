package foo.dbgroup.RDFstruct.schema;

import java.util.List;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.voidQuery.BuildExtractSchemaQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.mongo.dao.SchemaLODDAO;
import foo.dbgroup.mongo.entity.ClassLOD;
import foo.dbgroup.mongo.entity.EdgeLOD;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.SchemaLOD;

public class ExtractSchema {
	
	private EndPointSparql endPoint;

	QueryExecutorRemote queryExe;
	
	SchemaLODDAO schemaDAO;
	
	SchemaLOD schema;
	
	BuildExtractSchemaQuery schemaQuery;
	
	public ExtractSchema(Mongo mongo,Morphia morphia) {
		super();
		schemaDAO = new SchemaLODDAO(mongo,morphia);
		
	}

	public EndPointSparql getEndPoint() {
		return endPoint;
	}
	
	/*
	 * Must set the Dataset before extract and save schema
	 */

	public void setEndPoint(EndPointSparql endPoint) {
		this.endPoint = endPoint;
		queryExe=new QueryExecutorRemote();
		queryExe.setDataset(endPoint.getUri(), endPoint.getNome());
		//TODO
		//per il momento se eiste già in Mongo lo sovvrascrive
		//sarà necessario guardare se ci sono errori. Nel caso che non ve ne siano skipparlo.
		SchemaLOD sch=schemaDAO.findOne("uri", endPoint.getUri());
		if(sch!=null){
			System.out.println("dataset alredy present in Mongo : "+endPoint.getNome());
			sch.clearEdge();
			schema=sch;
		}else{
			schema=new SchemaLOD();
			schema.setId(endPoint.getId());
			schema.setNome(endPoint.getNome());
			schema.setUri(endPoint.getUri());
		}
		schemaQuery = new BuildExtractSchemaQuery();
		System.out.println("Setup complete");
	}
	
	public void extractAndSaveSchema(){
		System.out.println(" *****  "+endPoint.getNome()+" ***** ");
		System.out.println("Schema extraction started");
		
		GenericQuery startQuery =schemaQuery.start();
		queryExe.setQuery(startQuery);
		
		List<ClassLOD> clasLod=queryExe.createClass();

		List<GenericQuery> secondLQueries=schemaQuery.secondLQuery(clasLod);
		
		for(GenericQuery current : secondLQueries){
			queryExe.setQuery(current);
			List<EdgeLOD> curr=queryExe.addNode(clasLod);
			if(curr==null){
				EdgeLOD error=new EdgeLOD();
				error.setError(true);
				error.setsClass(current.getConstant());
				schema.addEdge(error);
			}else if(curr.size()!=0){
				for(EdgeLOD c:curr){
					System.out.println(c.toString());
					schema.addEdge(c);//add to graph
				}	
			}
		}
		
//		System.out.println(schema.toString());
		schemaDAO.save(schema);
	}
	
	
	
	
	
	

}
