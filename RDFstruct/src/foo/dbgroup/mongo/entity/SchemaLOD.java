package foo.dbgroup.mongo.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


@Entity
public class SchemaLOD {
	
	@Id
	private Integer id;
	
	private String uri;
	
	private String nome;
	
	@Embedded
	private List<EdgeLOD> edges;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<EdgeLOD> getEdges() {
		return edges;
	}

	public void setEdges(List<EdgeLOD> edges) {
		this.edges = edges;
	}
	
	public void addEdge(EdgeLOD edge){
		if(edges==null){
			edges=new ArrayList<EdgeLOD>();
		}
		boolean trovato=false;
		for(EdgeLOD current : edges){
			if((current.getsClass().compareTo(edge.getsClass())==0 &&
					current.getoClass().compareTo(edge.getoClass())==0&& 
					current.getProperty().compareTo(edge.getProperty())==0)||
					(current.isError()&&edge.isError() && current.getsClass().compareTo(edge.getsClass())==0 ) ){
				trovato=true;
				break;
			}
		}
		if(!trovato)edges.add(edge);
	}

	public void clearEdge() {
		edges=new ArrayList<EdgeLOD>();
	}

	@Override
	public String toString() {
		return "SchemaLOD [id=" + id + ", uri=" + uri + ", nome=" + nome
				+ ", edges number=" + edges.size() + "]";
	}
	
	
	

	
	

}
