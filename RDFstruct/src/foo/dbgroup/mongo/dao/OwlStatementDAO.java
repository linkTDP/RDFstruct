package foo.dbgroup.mongo.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

import foo.dbgroup.mongo.entity.OwlStatement;

public class OwlStatementDAO  extends BasicDAO<OwlStatement,String> {

	public OwlStatementDAO(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "RDFstruct");
		// TODO Auto-generated constructor stub
	}

}
