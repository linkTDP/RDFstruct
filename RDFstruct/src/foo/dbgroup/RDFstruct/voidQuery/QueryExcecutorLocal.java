package foo.dbgroup.RDFstruct.voidQuery;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

public class QueryExcecutorLocal extends GenericQueryExecutorImp<VirtGraph> {

	VirtGraph dataset;
	
	@Override
	public void setDataset(VirtGraph object) {
		dataset=object;
	}
	
	public void executeQuery(){
		System.out.println("\n"+getQuery().getTitle()+"\n");
		
		html+="\n#"+getQuery().getTitle()+"#\n";
		
		try{
			Query sparql = QueryFactory.create(this.getQuery().getQuery());
			VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create(sparql,dataset);
			setResult(vqe.execSelect());
		}catch(Exception e){
			System.out.println(e.getMessage());
			html+=e.getMessage();
		}
	}

	

	
	
	

}
