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

	public List<String> getBlankNode() {
		return blankNode;
	}

	public void setBlankNode(List<String> blankNode) {
		this.blankNode = blankNode;
	}
	
	public void addBlankNode(String blankNode){
		if(this.blankNode==null)this.blankNode=new ArrayList<String>();
		this.blankNode.add(blankNode);
	};

}
