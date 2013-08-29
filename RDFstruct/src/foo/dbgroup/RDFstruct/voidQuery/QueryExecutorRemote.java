package foo.dbgroup.RDFstruct.voidQuery;


import java.util.Date;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;

import foo.dbgroup.mongo.entity.DatasetEntity;
import foo.dbgroup.mongo.entity.DatasetResult;


public class QueryExecutorRemote extends GenericQueryExecutorImp<String> {

	
	String dataset;
	
	@Override
	public void setDataset(String object, String title) {
		dataset=object;
		DatasetResult a = new DatasetResult();
		a.setUri(dataset);
		if(title != null)a.setName(title);
		this.setResult(a);
	}
	
	public void executeQuery(){
		System.out.println("\n"+getQuery().getTitle()+"\n");
		DatasetEntity b=getEntity();
		
		setEntity(b);
		html+="\n#"+getQuery().getTitle()+"#\n";
		
		try{
			Query sparql = QueryFactory.create(this.getQuery().getQuery());
			
			QueryExecution qe = QueryExecutionFactory.sparqlService(dataset, sparql);
			
			setResultSet(qe.execSelect());
		}catch(Exception e){
			System.out.println(e.getMessage());
//			for ( StackTraceElement ele : e.getStackTrace())System.out.println(ele.getMethodName()+"   "+ele.getLineNumber());
//			
//			System.out.println(e.getStackTrace());
			
			DatasetEntity a=getEntity();
			
			a.setError(e.getMessage());
			setEntity(a);
			getResult().addEntity(getEntity());
			html+=e.getMessage();
			setResultSet(null);
		}
	}
	
	
}
