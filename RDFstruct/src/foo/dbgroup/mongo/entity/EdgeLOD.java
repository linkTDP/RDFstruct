package foo.dbgroup.mongo.entity;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
@Entity
public class EdgeLOD {

	private String sClass;
	private String property;
	private String dClass;
	@Id
	private ObjectId id;
	
	public EdgeLOD() {
		super();
	}
	
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getdClass() {
		return dClass;
	}
	public void setdClass(String dClass) {
		this.dClass = dClass;
	}
	
}
