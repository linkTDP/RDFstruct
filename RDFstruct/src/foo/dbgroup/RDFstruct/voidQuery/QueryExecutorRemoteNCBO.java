package foo.dbgroup.RDFstruct.voidQuery;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.sparql.engine.http.HttpQuery;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

import foo.dbgroup.mongo.entity.DatasetResult;
import foo.dbgroup.mongo.entity.DoubleResult;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.ResultAtom;


public class QueryExecutorRemoteNCBO extends GenericQueryExecutorImp<String> {

	String KEY="1a3ee296-2fd1-4412-8303-601b2170ea44";
	String dataset;
	private static String SERVICE = "http://sparql.bioontology.org/sparql/";
	private List<EndPointSparql> endPoint;
	
	
	@Override
	public void setDataset(String object, String title) {
		dataset=object;
		DatasetResult a = new DatasetResult();
		a.setUri(dataset);
		if(title != null)a.setName(title);
		this.setResult(a);
	}
	
	
	public void executeQuery(boolean injection){
		System.out.println("\n"+getQuery().getTitle()+"\n");
		ResultAtom b=getEntity();
		
		setEntity(b);
		html+="\n#"+getQuery().getTitle()+"#\n";
		
		try{
			Query query;
			if( injection )query = QueryFactory.create(this.getQuery().getQueryNCBO(getResult().getUri())) ;
			else query = QueryFactory.create(this.getQuery().getQuery());
			HttpQuery.urlLimit = 100000;
			
		 QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(SERVICE, query);
		 qexec.addParam("apikey", KEY);
		 ResultSet results = qexec.execSelect() ;
		setResultSet(results);
		}catch(Exception e){
			System.out.println(e.getMessage());
			String trace="";
			for ( StackTraceElement ele : e.getStackTrace()){
				trace=trace+(ele.getClassName()+"."+ele.getClassName()+" "+ele.getLineNumber()+"\n");
			}
//			System.out.println(trace);
//			
//			System.out.println(e.getStackTrace().toString());
			
			
			ResultAtom a=getEntity();
			a.setError(e.getMessage());
			a.setTrace(trace);
			setEntity(a);
			getResult().addEntity(getEntity());
			html+=e.getMessage();
			setResultSet(null);
		}
		
//		System.out.println("\n"+getQuery().getTitle()+"\n");
//		ResultAtom b=getEntity();
//		
//		setEntity(b);
//		html+="\n#"+getQuery().getTitle()+"#\n";
//		
//		try{
//			Query sparql = QueryFactory.create(this.getQuery().getQuery());
//			QueryExecution qe = QueryExecutionFactory.sparqlService(dataset, sparql);
//			qe.setTimeout(50000, TimeUnit.MILLISECONDS);
//			ResultSet curresult=qe.execSelect();
//			setResultSet(curresult);
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//			String trace="";
//			for ( StackTraceElement ele : e.getStackTrace()){
//				trace=trace+(ele.getClassName()+"."+ele.getClassName()+" "+ele.getLineNumber()+"\n");
//			}
////			System.out.println(trace);
////			
////			System.out.println(e.getStackTrace().toString());
//			
//			
//			ResultAtom a=getEntity();
//			a.setError(e.getMessage());
//			a.setTrace(trace);
//			setEntity(a);
//			getResult().addEntity(getEntity());
//			html+=e.getMessage();
//			setResultSet(null);
//		}
		
		
		
		
	}
	
	public List<EndPointSparql> getEndPointNCBO(){
		this.executeQuery(false);
		if(getResultSet()!=null){
			while (getResultSet().hasNext()) {
				
				EndPointSparql current=new EndPointSparql();
				
				QuerySolution result = getResultSet().nextSolution();
				
				for(int i = 0;i<getQuery().getParameters().size();i++){
					RDFNode a=result.get(getQuery().getParameters().get(i));
					if(i!=0){
						System.out.print("  --  ");
						html+="  --  ";
					}
					
					String cur="";
					if(a.toString().contains("^^http://www.w3.org/2001/XMLSchema#integer")||a.toString().contains("^^http://www.w3.org/2001/XMLSchema#int")){
						cur=a.toString().replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#integer", "");
						cur=cur.replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#int", "");
						System.out.print(cur);
						html+=cur;
					}else{
						if(a.toString().contains("^^http://www.w3.org/2001/XMLSchema#string")){
							cur=a.toString().replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#string", "");
							System.out.print(cur);
							html+=cur;
						}else{
							cur=a.toString();
							System.out.print(cur);
						}
					}
					
					
					
					if(getQuery().getParameters().get(i).contains("?ont"))current.setVersion(cur);
					if(getQuery().getParameters().get(i).contains("?graph"))current.setUri(cur);
					if(getQuery().getParameters().get(i).contains("?acr"))current.setAcronym(cur);
					if(getQuery().getParameters().get(i).contains("?name"))current.setNome(cur);
					
					
					
				
				}
				current.setNCBO(true);
				addEndPoint(current);
				System.out.print("\n");
				html+="\n";
				
			}
			
			
		
		
		}
		return getEndPoint();
	}
	
	
	public List<EndPointSparql> getEndPoint() {
		return endPoint;
	}



	public void setEndPoint(List<EndPointSparql> endPoint) {
		this.endPoint = endPoint;
	}
	
	public void addEndPoint(EndPointSparql element) {
		if(endPoint == null) endPoint=new ArrayList<EndPointSparql>();
		endPoint.add(element);
	}


	@Override
	@Deprecated
	public void executeQuery() {
		// TODO Auto-generated method stub
		
	}
	
	
}
