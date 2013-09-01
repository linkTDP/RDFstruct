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
}
