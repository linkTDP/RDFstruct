package foo.dbgroup.mongo.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassLOD {
	
	
	private String clas;
	
	
	private List<String> blankNode;

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	@Deprecated
	public List<String> getBlankNode() {
		return blankNode;
	}

	@Deprecated
	public void setBlankNode(List<String> blankNode) {
		this.blankNode = blankNode;
	}
	
	@Deprecated
	public void addBlankNode(String blankNode){
		if(this.blankNode==null)this.blankNode=new ArrayList<String>();
		this.blankNode.add(blankNode);
	};

}
