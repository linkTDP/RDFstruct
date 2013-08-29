package foo.dbgroup.RDFstruct;

import java.net.UnknownHostException;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.voidQuery.BuildVoidQuery;
import foo.dbgroup.mongo.dao.GenericQueryDAO;
import foo.dbgroup.mongo.entity.GenericQuery;

public class PopulateDbQuery {

	public static void main(String[] args) {
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		
		GenericQueryDAO queryDao=new GenericQueryDAO( mongo,morphia);
		
		BuildVoidQuery Void=new BuildVoidQuery();
		
		for(GenericQuery cur: Void.getVoidQuery()){
			queryDao.save(cur);
		}
		
		

	}

}
