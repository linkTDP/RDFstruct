package foo.dbgroup.RDFstruct.voidQuery;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.markdown4j.Markdown4jProcessor;

import com.google.code.morphia.Datastore;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import foo.dbgroup.mongo.dao.DatasetResultDAO;
import foo.dbgroup.mongo.entity.ClassLOD;
import foo.dbgroup.mongo.entity.DatasetResult;
import foo.dbgroup.mongo.entity.DoubleResult;
import foo.dbgroup.mongo.entity.EdgeLOD;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.MyTriple;
import foo.dbgroup.mongo.entity.ResultAtom;

public abstract class GenericQueryExecutorImp<T> implements GenericQueryExecutor<T> {

	private GenericQuery query;
	private ResultSet results;
	String html="";
	private ResultAtom entity;
	private DatasetResult result;
	
	
	
	
	
	
	@Override
	public void setQuery(GenericQuery q) {
		query=q;
		setResultSet(null);
		//se c'è gia un elemento lo rimuovo
		ResultAtom find=null;
		if(getResult()!=null&&getResult().getQueryResult()!=null){
			for(ResultAtom current : getResult().getQueryResult()){
				if(current.getQueryNumber() == q.getNumber()){
					find=current;
				}
			}
		}
		
		if(find!= null)getResult().removeEntity(find);
		
		ResultAtom current = new ResultAtom();
		current.setTitle(q.getTitle());
		current.setQueryNumber(q.getNumber());
		current.setTime(new Date());
		setEntity(current);
	}

	

	@Override
	public void printResult() {
		if(results!=null){
		while (results.hasNext()) {
			QuerySolution result = results.nextSolution();
			DoubleResult doub=new DoubleResult();
			for(int i = 0;i<query.getParameters().size();i++){
				RDFNode a=result.get(query.getParameters().get(i));
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
				if(query.getParameters().size()==1 && query.getParameters().get(i).contains("?no")){
					getEntity().setCount(Integer.parseInt(cur));
				}
				if(query.getParameters().size()==1 && query.getParameters().get(i).contains("?list")){
					getEntity().addResult(cur);
				}
				if(query.getParameters().size()==2&&(query.getParameters().get(i).contains("?p")||query.getParameters().get(i).contains("?count"))){
					if (i==0)doub.setProp(cur);
					if(i==1){
						doub.setCount(Integer.parseInt(cur));
						getEntity().addCresult(doub);
					}
				}
			
			}
			System.out.print("\n");
			html+="\n";
			
		}
		if(getResult()!=null){
			getResult().addEntity(getEntity());
		}
		}else{
			
			System.out.print("\n");
			html+="\n";
		}
		setEntity(null);
		
	}

	@Override
	public GenericQuery getQuery() {
		
		return query;
	}


	@Override
	public void setResultSet(ResultSet r) {
		results=r;
		
	}

	@Override
	public void printMarkDown(String name) {
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( name+".html"));
		    String htmlRerult = new Markdown4jProcessor().process(html);
		    writer.write( htmlRerult);

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
		html="";
	}

	public ResultAtom getEntity() {
		return entity;
	}

	public void setEntity(ResultAtom entity) {
		this.entity = entity;
	}
	
	public void saveToMongo(DatasetResultDAO ds){
		ds.save(getResult());
		
		setResult(null);
	}

	public DatasetResult getResult() {
		return result;
	}

	public void setResult(DatasetResult result) {
		this.result = result;
	}



	public ResultSet getResultSet() {
		return results;
	}



	public List<MyTriple> getTriple(){
		List<MyTriple> mieTriple=new ArrayList<MyTriple>();
		if(results!=null&&getQuery().getConstant()!=null){
			
			while (results.hasNext()) {
				QuerySolution result = results.nextSolution();
				MyTriple currentTriple=new MyTriple();
				if(getQuery().isObject())currentTriple.setObject(getQuery().getConstant());
				if(getQuery().isPredicate())currentTriple.setPredicate(getQuery().getConstant());
				if(getQuery().isSubject())currentTriple.setSubject(getQuery().getConstant());
				for(int i = 0;i<query.getParameters().size();i++){
					RDFNode a=result.get(query.getParameters().get(i));
					
					String cur=a.toString();
					if(query.getParameters().get(i).compareTo("?a")==0){
						currentTriple.setSubject(cur);
					}
					if(query.getParameters().get(i).compareTo("?b")==0){
						currentTriple.setPredicate(cur);
					}
					if(query.getParameters().get(i).compareTo("?c")==0){
						currentTriple.setObject(cur);
					}
				}
			mieTriple.add(currentTriple);	
			}
		}
		return mieTriple;
	}
		
	
	
	public List<ClassLOD> createClass() {
		List<ClassLOD> mieClassi=new ArrayList<ClassLOD>();
		if(results!=null){
			
			while (results.hasNext()) {
				QuerySolution result = results.nextSolution();
				ClassLOD currentClass=new ClassLOD();
				RDFNode a=result.get(query.getParameters().get(0));
				String cur=a.toString();
				currentClass.setClas(cur);
				mieClassi.add(currentClass);
			}
		}
		return mieClassi;
	}
	
	public List<EdgeLOD> addNode(List<ClassLOD> clasLod) {
		
		List<EdgeLOD> edge=new ArrayList<EdgeLOD>();
		if(results!=null){
			while (results.hasNext()) {
				EdgeLOD current=new EdgeLOD();
				int ind=0;
				String sClassSub="";
				ind=getQuery().getConstant().lastIndexOf('/');
				sClassSub=getQuery().getConstant().substring(ind+1, getQuery().getConstant().length());
				if(sClassSub.length()>0)current.setsClass(sClassSub);
				else current.setsClass(getQuery().getConstant());
				QuerySolution result = results.nextSolution();
				for(int i = 0;i<query.getParameters().size();i++){
					RDFNode a=result.get(query.getParameters().get(i));
					String cur=a.toString();
					int index =0;
					if(cur!=null && cur.length()>0)index=cur.lastIndexOf('/');
					if(index!=0)cur=cur.substring(index+1, cur.length());
					if(query.getParameters().get(i).compareTo("?Popietry")==0)current.setProperty(cur);
					if(query.getParameters().get(i).compareTo("?OClass")==0)current.setoClass(cur);
//					System.out.println(cur);
				}
				edge.add(current);
			}
		}
		return edge;
	}
	







	



	




	
}
