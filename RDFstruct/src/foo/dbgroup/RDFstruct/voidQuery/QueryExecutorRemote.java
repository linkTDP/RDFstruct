package foo.dbgroup.RDFstruct.voidQuery;



import java.util.concurrent.TimeUnit;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

import foo.dbgroup.mongo.entity.DatasetResult;
import foo.dbgroup.mongo.entity.ResultAtom;


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
		ResultAtom b=getEntity();
		
		setEntity(b);
		html+="\n#"+getQuery().getTitle()+"#\n";
		
		try{
			Query sparql = QueryFactory.create(this.getQuery().getQuery());
			
			QueryExecution qe = QueryExecutionFactory.sparqlService(dataset, sparql);
			qe.setTimeout(20000, TimeUnit.MILLISECONDS);
			ResultSet curresult=qe.execSelect();
			setResultSet(curresult);
		}catch(Exception e){
			System.out.println(e.getMessage());
//			for ( StackTraceElement ele : e.getStackTrace())System.out.println(ele.getMethodName()+"   "+ele.getLineNumber());
//			
//			System.out.println(e.getStackTrace());
			
			ResultAtom a=getEntity();
			
			a.setError(e.getMessage());
			setEntity(a);
			getResult().addEntity(getEntity());
			html+=e.getMessage();
			setResultSet(null);
		}
	}
	
	
}
