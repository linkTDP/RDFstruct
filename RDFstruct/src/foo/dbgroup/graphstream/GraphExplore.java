package foo.dbgroup.graphstream;

import java.util.Iterator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class GraphExplore {
    public static void main(String args[]) {
        new GraphExplore();
    }

    public GraphExplore() {
        Graph graph = new SingleGraph("tutorial 1");

        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();

        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("AD", "A", "D");
        graph.addEdge("DE", "D", "E");
        graph.addEdge("DF", "D", "F");
        graph.addEdge("EF", "E", "F");

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
        
        for (Edge edge : graph.getEachEdge()){
        	edge.addAttribute("ui.label", "prova");
        	
        }

        graph.addEdge("FA","F","A");
        sleep();
        graph.addEdge("UA","U","A");
        
    }

//    public void explore(Graph source) {
////        Iterator<? extends Node> k = source.getBreadthFirstIterator();
//
//        for(Integer i=0; i<10 ; i++){
//        	Integer t=i+1;
//        	source.addEdge(i.toString()+t.toString(), i.toString(), t.toString());
//        	sleep();
//        }
//        
//    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    protected String styleSheet =
        "node {" +
        "   fill-color: black;" +
        "}" +
        "node.marked {" +
        "   fill-color: red;" +
        "}";
}
