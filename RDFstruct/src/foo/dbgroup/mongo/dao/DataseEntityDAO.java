package foo.dbgroup.mongo.dao;

import org.bson.types.ObjectId;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.entity.DatasetEntity;

public class DataseEntityDAO extends BasicDAO<DatasetEntity, ObjectId> {

	protected DataseEntityDAO(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "RDFstruct");
		// TODO Auto-generated constructor stub
	}

}
