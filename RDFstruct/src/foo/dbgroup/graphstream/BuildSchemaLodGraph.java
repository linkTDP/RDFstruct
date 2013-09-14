package foo.dbgroup.graphstream;

import java.util.List;

import foo.dbgroup.RDFstruct.voidQuery.BuildExtractOntoQuery;
import foo.dbgroup.RDFstruct.voidQuery.BuildExtractSchemaQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.mongo.entity.ClassLOD;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;

public class BuildSchemaLodGraph {

	BuildExtractSchemaQuery schemaQuery;
	QueryExecutorRemote queryExe;
	
	
	public BuildSchemaLodGraph(EndPointSparql e) {
		super();
		queryExe = new QueryExecutorRemote();
		queryExe.setDataset(e.getUri(), e.getNome());
		schemaQuery = new BuildExtractSchemaQuery();
	}
	
	
	
	public void generateGraph(){
		GenericQuery startQuery =schemaQuery.start();
		queryExe.setQuery(startQuery);
		queryExe.executeQuery();
		List<ClassLOD> clasLod=queryExe.createClass();
//		for(ClassLOD c : clasLod)System.out.println(c.getClas());
		List<GenericQuery> secondLQueries=schemaQuery.secondLQuery(clasLod);
		for(GenericQuery current : secondLQueries){
			queryExe.setQuery(current);
			queryExe.executeQuery();
			queryExe.addNode(clasLod);
		}
		for(ClassLOD current : clasLod){
			System.out.println(current.getClas());
			if(current.getBlankNode()!=null)for(String cu : current.getBlankNode())System.out.println(cu);
			System.out.println();
		}
	}
	
	
	
	
	
}
