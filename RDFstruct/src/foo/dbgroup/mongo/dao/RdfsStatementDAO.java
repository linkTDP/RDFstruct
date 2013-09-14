package foo.dbgroup.mongo.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.entity.OwlStatement;
import foo.dbgroup.mongo.entity.RdfsStatement;

public class RdfsStatementDAO  extends BasicDAO<RdfsStatement,String> {

	public RdfsStatementDAO(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "RDFstruct");
		// TODO Auto-generated constructor stub
	}

}
