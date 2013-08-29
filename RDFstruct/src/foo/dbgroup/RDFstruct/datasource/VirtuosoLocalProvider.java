package foo.dbgroup.RDFstruct.datasource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import virtuoso.jena.driver.VirtGraph;

public class VirtuosoLocalProvider implements Dataset {

	
	public VirtuosoLocalProvider() {
		super();
	}

	public VirtuosoLocalProvider(String dataset, String user, String password) {
		super();
		this.dataset = dataset;
		this.user = user;
		this.password = password;
	}

	protected final Log log = LogFactory.getLog(VirtuosoLocalProvider.class); 
	
	private String connection="jdbc:virtuoso://localhost:1111/";
	private String dataset;
	private String user;
	private String password;
	private VirtGraph VGraph;
	
	@Override
	public void setConnectionString(String conn) {
		connection = conn;
	}

	@Override
	public void setDataset(String dataset) {
		this.dataset=dataset;
		
	}

	@Override
	public boolean testConnetion() {
		if(user == null || password == null || dataset == null){
			if(user==null){
				log.error("The user is not set");
				return false;
			}
			if(password==null){
				log.error("The password is not set");
				return false;
			}
			if(dataset==null){
				log.error("The dataset is not set");
				return false;
			}
		}else{
			VGraph=new VirtGraph (dataset,connection, user, password);
			if(VGraph.getCount()>0)return true;
			else return false;
		}
		return false;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public VirtGraph getVgraph(){
		if(testConnetion())VGraph=new VirtGraph (dataset,connection, user, password);
		return VGraph;
	}

}
