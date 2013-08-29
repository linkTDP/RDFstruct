package foo.dbgroup.mongo.entity;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


@Entity
public class EndPointSparql {
	
	@Id
	private ObjectId id;
	
	private String uri;
	
	private String nome;
	
	

	public EndPointSparql() {
		super();
	}

	public EndPointSparql(String uri, String nome) {
		super();
		this.uri = uri;
		this.nome = nome;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	
	

}
