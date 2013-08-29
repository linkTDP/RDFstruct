package foo.dbgroup.RDFstruct;

import java.io.InputStream;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFVisitor;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class TestLoadingJena {
	
	
	public static void main(String[] args) {
		
		// create an empty model
		 Model model = ModelFactory.createDefaultModel();
	
		 // use the FileManager to find the input file
		InputStream in = FileManager.get().open("adms");
		
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File:  not found");
		}
	
		// read the RDF/XML file
		model.read(in,null);
	
		// write it to standard out
		model.write(System.out);
		
//		System.out.println(model.containsLiteral(new Resource, arg1, arg2)contains());
	}
}
