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
	
	private String acronym;
	
	private String version;
	
	private boolean NCBO;
	
	

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

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isNCBO() {
		return NCBO;
	}

	public void setNCBO(boolean nCBO) {
		NCBO = nCBO;
	}



}
