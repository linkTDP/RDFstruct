package foo.dbgroup.RDFstruct.datasource;

/**
 * @author link
 *
 */
public interface Dataset {

	/**
	 * 
	 * @param String conn - Connection string to the source
	 */
	
	public void setConnectionString(String conn);
	
	/**
	 * 
	 * @param String dataset - String name of the Dataset
	 */
	
	public void setDataset(String dataset);
	
	/**
	 * 
	 * @return boolean - test if the connection and dataset are reachable
	 */
	
	public boolean testConnetion();
	
}
