package foo.dbgroup.mongo.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class DatasetResult {

	@Id
	private ObjectId id;
	
	private String uri;
	
	private String name;

	@Embedded
	private
	List<ResultAtom> queryResult;
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResultAtom> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(List<ResultAtom> queryResult) {
		this.queryResult = queryResult;
	}
	
	public void addEntity(ResultAtom element) {
		if(queryResult == null) queryResult=new ArrayList<ResultAtom>();
		queryResult.add(element);
	}
	
	public void removeEntity(ResultAtom element){
		queryResult.remove(element);
	}
	
	public List<ResultAtom> getQueryResultNoSchema(){
		List<ResultAtom> queryResultNoSchema=new ArrayList<ResultAtom>();
		for(ResultAtom current : queryResult){
			if(current.getCount()==null&&current.getError()==null){
				if(current.getResult()!=null){
					List<String> curRes=new ArrayList<String>();
					for(String cstri:current.getResult()){
						if(!(cstri.contains("http://www.openlinksw.com/schemas")||
								cstri.contains("http://www.w3.org/ns/sparql-service-description#")||
								cstri.contains("http://www.w3.org/2002/07/owl#")||
								cstri.contains("http://www.w3.org/2000/01/rdf-schema#")||
								cstri.contains("http://www.w3.org/1999/02/22-rdf-syntax-ns"))){
							curRes.add(cstri);
						}
					}
					ResultAtom tmp=current;
					tmp.setResult(curRes);
					queryResultNoSchema.add(tmp);
				}
				if(current.getCresult()!=null){
					List<DoubleResult> curRes=new ArrayList<DoubleResult>();
					for(DoubleResult cstri:current.getCresult()){
						if(!(cstri.getProp().contains("http://www.openlinksw.com/schemas")||
								cstri.getProp().contains("http://www.w3.org/ns/sparql-service-description#")||
								cstri.getProp().contains("http://www.w3.org/2002/07/owl#")||
								cstri.getProp().contains("http://www.w3.org/2000/01/rdf-schema#")||
								cstri.getProp().contains("http://www.w3.org/1999/02/22-rdf-syntax-ns"))){
							curRes.add(cstri);
						}
					}
					ResultAtom tmp=current;
					tmp.setCresult(curRes);
					queryResultNoSchema.add(tmp);
				}
			}else{
				queryResultNoSchema.add(current);
			}
		}
		return queryResultNoSchema;
	}
}
