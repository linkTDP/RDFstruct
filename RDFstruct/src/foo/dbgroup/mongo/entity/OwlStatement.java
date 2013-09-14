package foo.dbgroup.mongo.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class OwlStatement {

	@Id
	private String statement;
	
	private boolean predicate;
	
	

	public OwlStatement() {
		super();
	}

	public OwlStatement(String statement) {
		this.statement=statement;
	}

	public OwlStatement(String statement, boolean b) {
		this.statement=statement;
		this.predicate=b;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public boolean isPredicate() {
		return predicate;
	}

	public void setPredicate(boolean predicate) {
		this.predicate = predicate;
	}
	
}
