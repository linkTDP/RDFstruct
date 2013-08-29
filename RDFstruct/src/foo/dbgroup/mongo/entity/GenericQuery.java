package foo.dbgroup.mongo.entity;

import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class GenericQuery {
	
	private String query;
	private List<String> parameters;
	private String title;
	
	@Id
	private Integer number;
	
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
	public List<String> getParameters() {
		return parameters;
	}
	
	
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

}
