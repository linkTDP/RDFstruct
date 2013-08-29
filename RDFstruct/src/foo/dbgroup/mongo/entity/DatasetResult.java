package foo.dbgroup.mongo.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

@Entity
public class DatasetResult {

	private String uri;
	
	private String name;

	@Embedded
	private
	List<DatasetEntity> queryResult;
	
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

	public List<DatasetEntity> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(List<DatasetEntity> queryResult) {
		this.queryResult = queryResult;
	}
	
	public void addEntity(DatasetEntity element) {
		if(queryResult == null) queryResult=new ArrayList<DatasetEntity>();
		queryResult.add(element);
	}
}
