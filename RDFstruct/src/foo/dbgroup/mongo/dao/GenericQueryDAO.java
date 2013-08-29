package foo.dbgroup.mongo.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.entity.GenericQuery;

public class GenericQueryDAO extends BasicDAO<GenericQuery,Integer > {

	public GenericQueryDAO(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "RDFstruct");
		// TODO Auto-generated constructor stub
	}

}
