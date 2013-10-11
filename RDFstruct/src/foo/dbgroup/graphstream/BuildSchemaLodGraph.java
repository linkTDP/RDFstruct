package foo.dbgroup.graphstream;

import java.util.HashMap;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.AbstractEdge;
import org.graphstream.graph.implementations.AbstractGraph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.MultiNode;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.graphicGraph.GraphicEdge;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.graphicGraph.GraphicNode;
import org.graphstream.ui.swingViewer.Viewer;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import foo.dbgroup.RDFstruct.voidQuery.BuildExtractOntoQuery;
import foo.dbgroup.RDFstruct.voidQuery.BuildExtractSchemaQuery;
import foo.dbgroup.RDFstruct.voidQuery.QueryExecutorRemote;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.dao.SchemaLODDAO;
import foo.dbgroup.mongo.entity.ClassLOD;
import foo.dbgroup.mongo.entity.EdgeLOD;
import foo.dbgroup.mongo.entity.EndPointSparql;
import foo.dbgroup.mongo.entity.GenericQuery;
import foo.dbgroup.mongo.entity.SchemaLOD;

public class BuildSchemaLodGraph {

	BuildExtractSchemaQuery schemaQuery;
	QueryExecutorRemote queryExe;
	AbstractGraph graph;
	private EndPointSparql endpoint;
	
	
	public BuildSchemaLodGraph() {
		super();
		
		
		graph = new MultiGraph("Test");
		
		graph.addAttribute("ui.antialias");
		graph.addAttribute("layout.gravity", 0.03);
		graph.addAttribute("layout.quality", 4);
		graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
		
	}
	
	
	
	public void generateGraph(EndPointSparql e){
		queryExe = new QueryExecutorRemote();
		queryExe.setDataset(e.getUri(), e.getNome());
		schemaQuery = new BuildExtractSchemaQuery();

        
        
        Viewer viewer = graph.display();
       
		GenericQuery startQuery =schemaQuery.start();
		queryExe.setQuery(startQuery);
//		queryExe.executeQuery();
		List<ClassLOD> clasLod=queryExe.createClass();
//		for(ClassLOD c : clasLod)System.out.println(c.getClas());
		List<GenericQuery> secondLQueries=schemaQuery.secondLQuery(clasLod);
		for(GenericQuery current : secondLQueries){
			queryExe.setQuery(current);
//			queryExe.executeQuery();
			for(EdgeLOD c:queryExe.addNode(clasLod)){
				System.out.println(c.toString());
				addEdge(c);//add to graph
			}
			
		}
		
		for(ClassLOD current : clasLod){
			System.out.println(current.getClas());
			if(current.getBlankNode()!=null)for(String cu : current.getBlankNode())System.out.println(cu);
			System.out.println();
		}
	}
	
	/*
	 * Draw SchemaLOD tacked from Mongo
	 */
	
	public void drowGraphFromMongo( SchemaLOD schem){

		
		SchemaLOD schema=schem;
		
		if(schema!=null){
			
	        
	        Viewer viewer = graph.display();
	        
	        for(EdgeLOD current : schema.getEdges()){
	        	if(!current.isError())addEdge(current);
	        }
		}
	}
	
	
	private void addEdge(EdgeLOD c) {
	
		c.setsClass(sbstringClass(c.getsClass()));
		c.setoClass(sbstringClass(c.getoClass()));
		c.setProperty(sbstringClass(c.getProperty()));
		
		
//		cur=new HashMap<String,Object>();
//		cur.put("ui.label", c.getoClass());
		
		
		
//		GraphicNode subject=new GraphicNode(graph , c.getsClass(),cur);
		
		
//		GraphicNode object=new GraphicNode(graph, c.getoClass(), cur);
		
		
//		cur=new HashMap<String,Object>();
//		cur.put("ui.label", c.getProperty());	

		
		
		//		Edge predicate= new AbstractEdge( c.getProperty(), subject, object, true);
		
		graph.addEdge(c.getProperty(), c.getsClass(), c.getoClass(), true);
//		GraphicEdge propriety=new GraphicEdge( c.getProperty(), subject, object, true, cur);
		
//		graph.addEdge(c.getProperty(), c.getsClass(), c.getoClass());
		
		
		HashMap<String,Object> cur=new HashMap<String,Object>();
		cur.put("ui.label", c.getsClass());
		
		if(graph.getNode(c.getsClass())!=null){
			graph.getNode(c.getsClass()).addAttributes(cur);
		}
		else System.out.println("##### "+c.getsClass());
		if(c.getoClass().compareTo(c.getsClass())!=0&&graph.getNode(c.getoClass())!=null)
			graph.getNode(c.getoClass()).addAttribute("ui.label", c.getoClass());
		
		if(graph.getEdge(c.getProperty())!=null){
			graph.getEdge(c.getProperty()).setAttribute("ui.label", c.getProperty());
		}
	}

	/*
	 * Return the final part of the uri ( Class or propriety )
	 */

	private String sbstringClass(String uri) {
		int ind=uri.lastIndexOf('/');
		return uri.substring(ind+1, uri.length());
	}


	public EndPointSparql getEndpoint() {
		return endpoint;
	}



	public void setEndpoint(EndPointSparql endpoint) {
		this.endpoint = endpoint;
	}


	protected String styleSheet =
			//shape: circle; fill-color: #EEE; stroke-mode: plain; stroke-color: #333; size:30px;
			"node {" +
					"size-mode: fit;"+
			"padding: 4px; "+
	        "   fill-color: #EEE;" +
	        "	shape: circle;"+
	        "	stroke-mode: plain;"+
	        "	stroke-color: #333;"+
//	        "	size:70px, 30px;"+
	        "}" +
	        "node.marked {" +
	        "   fill-color: red;" +
	        "}"+
	        "graph {"+
	        "padding:  30px; "+ //fill-color: #EEE; colora grafo
	        "}"
	        ;
	
	
	
	
	
}
