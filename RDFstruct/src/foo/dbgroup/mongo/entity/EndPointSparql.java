package foo.dbgroup.mongo.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


@Entity
public class EndPointSparql {
	
	@Id
	private String uri;
	
	private String nome;
	
	

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
	
	
	
	
	
	
	
	
	

}
