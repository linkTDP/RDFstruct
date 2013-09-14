package foo.dbgroup.mongo.entity;

import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class GenericQuery {
	
	private String prequery;
	private String postquery;
	private List<String> parameters;
	private String title;
	private String absoluteQuery;
	
	/*  Used for Ontology (owl+Rdfs) extraction      */
	private String constant;
	private boolean predicate;
	private boolean subject;
	private boolean object;
	
	@Id
	private Integer number;
	
	
	
	public String getQuery() {
		if(absoluteQuery!=null)return absoluteQuery;
		else return getPrequery()+getPostquery();
	}
	
	public String getQueryNCBO(String dataset) {
		return getPrequery()+" FROM <"+dataset+"> "+getPostquery();
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



	public String getPrequery() {
		return prequery;
	}



	public void setPrequery(String prequery) {
		this.prequery = prequery;
	}



	public String getPostquery() {
		return postquery;
	}



	public void setPostquery(String postquery) {
		this.postquery = postquery;
	}

	public String getAbsoluteQuery() {
		return absoluteQuery;
	}

	public void setAbsoluteQuery(String absoluteQuery) {
		this.absoluteQuery = absoluteQuery;
	}

	public String getConstant() {
		return constant;
	}

	public void setConstant(String constant) {
		this.constant = constant;
	}

	public boolean isPredicate() {
		return predicate;
	}

	public void setPredicate(boolean predicate) {
		this.predicate = predicate;
	}

	public boolean isSubject() {
		return subject;
	}

	public void setSubject(boolean subject) {
		this.subject = subject;
	}

	public boolean isObject() {
		return object;
	}

	public void setObject(boolean object) {
		this.object = object;
	}

}
