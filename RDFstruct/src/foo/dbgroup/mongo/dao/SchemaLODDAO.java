package foo.dbgroup.mongo.dao;

import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.entity.SchemaLOD;

public class SchemaLODDAO extends BasicDAO<SchemaLOD,String> {

	public SchemaLODDAO(Mongo mongo, Morphia morphia) {
		super(mongo, morphia,  "RDFstruct");
		// TODO Auto-generated constructor stub
	}
	
	public List<SchemaLOD> getSchemaWithEdge(){
		return ds.find(SchemaLOD.class).field("edges").exists().asList();
	}
	
	/*
	 * db.SchemaLOD.findOne({$and:[{'edges':{$not:{$exists:{error:true}}}},{$exists:'edges'}]})
	 */

}
