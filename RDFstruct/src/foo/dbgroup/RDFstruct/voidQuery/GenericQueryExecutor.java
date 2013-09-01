package foo.dbgroup.RDFstruct.voidQuery;

import com.google.code.morphia.Datastore;
import com.hp.hpl.jena.query.ResultSet;

import foo.dbgroup.mongo.dao.DatasetResultDAO;
import foo.dbgroup.mongo.entity.GenericQuery;

public interface GenericQueryExecutor<T> {

	/**
	 * Load a GenericQuery to be executed
	 * @param GenericQuery q
	 */
	public void setQuery(GenericQuery q);
	
	/**
	 * Load a Dataset to where the query is executed (local or remote)
	 * @param T object
	 */
	abstract void setDataset(T object, String Title);
	
	/**
	 * Print the result on the console
	 * @param T object
	 */
	public void printResult();
	
	/**
	 * Return the Generic Query setted before
	 * @return Generic query
	 */
	public GenericQuery getQuery();
	
	
	/**
	 * Set the result set as result of the query
	 * @param ResultSet r
	 */
	public void setResultSet(ResultSet r);
	
	/**
	 * Procuce in output a file html containing the result of analisis
	 * @param 
	 */
	public void printMarkDown(String name);
	
	//TODO commentare
	public void saveToMongo(DatasetResultDAO ds);
	
}
