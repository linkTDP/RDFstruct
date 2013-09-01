package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.datasource.EndpointRepository;
import foo.dbgroup.RDFstruct.datasource.VirtuosoLocalProvider;
import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExcecutorLocal;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.mongo.dao.DatasetResultDAO;
import foo.dbgroup.mongo.entity.DatasetResult;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.ResultAtom;

public class PopulateDatasetResult {

	public static void main(String[] args) {
//		VirtuosoLocalProvider a = new VirtuosoLocalProvider("SnomedCT","dba","dba");
//		
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		
		BuildVoidQuery qu=new BuildVoidQuery();
		
		List<GenericQuery> liqu=qu.getVoidQuery();
		
		
		
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		
//		QueryExcecutorLocal exe=new QueryExcecutorLocal();
//		
//		exe.setDataset(a.getVgraph());
		Morphia morphia = new Morphia();
		
		DatasetResultDAO rDao=new DatasetResultDAO(mongo, morphia);
		
		Datastore ds = morphia.createDatastore(mongo, "RDFstruct");
		
		
		QueryExecutorRemote exe = new QueryExecutorRemote();
		
		EndpointRepository repo= new EndpointRepository();
		
		for(EndPointSparql end:repo.getLista()){
			
			//recuera dataset
			
			DatasetResult d=rDao.findOne("uri", end.getUri());
			
//			System.out.println(d.getUri());
			
			exe.setDataset(end.getUri(),end.getNome());
			
			if(d!=null){
				exe.setResult(d);
				System.out.println("mach found! overwrite DatasetResult");
			}
			
			boolean changes=false;
			
			for (GenericQuery current :liqu ){
				
				boolean skip=false;
				if(d!=null){
					for(ResultAtom atom : d.getQueryResult()){
						if(atom.getQueryNumber()==current.getNumber() && atom.getError()==null)skip=true;
					}
				}
				
				if(!skip){
					exe.setQuery(current);
					exe.executeQuery();
					exe.printResult();
					changes=true;
				}else{
					System.out.println("skipped query :"+current.getTitle());
				}
				
			}
			if(changes){
				System.out.println("Changes found. Try to update");
				exe.saveToMongo(rDao);
			}
//			exe.printMarkDown(end.getNome());
		}
//		System.out.println(a.testConnetion());

	}

}
