package foo.dbgroup.mongo.entity;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


@Entity
public class EndPointSparql {
	
	@Id
	private Integer id;
	
	private String uri;
	
	private String nome;
	
	

	public EndPointSparql() {
		super();
	}

	public EndPointSparql(String uri, String nome, Integer id) {
		super();
		this.uri = uri;
		this.nome = nome;
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	
	
	
	
	
	
	
	
	

}
