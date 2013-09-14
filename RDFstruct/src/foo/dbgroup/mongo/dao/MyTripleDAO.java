package foo.dbgroup.mongo.dao;

import org.bson.types.ObjectId;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.entity.DatasetResult;
import foo.dbgroup.mongo.entity.MyTriple;

public class MyTripleDAO extends BasicDAO<MyTriple, ObjectId> {

	public MyTripleDAO(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "RDFstruct");
		// TODO Auto-generated constructor stub
	}

}
