package foo.dbgroup.RDFstruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtModel;

import com.hp.hpl.jena.graph.BulkUpdateHandler;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class Import {

	public static void main( String[] args )
    {
		
//		Model model = ModelFactory.createDefaultModel();
//
//		String inputFileName= "countries.rdf";
//		 // use the FileManager to find the input file
//		 InputStream in = FileManager.get().open( inputFileName );
//		if (in == null) {
//		    throw new IllegalArgumentException(
//		                                 "File: " + inputFileName + " not found");
//		}
//
//		// read the RDF/XML file
//		model.read(in, null);
//
		String url = "jdbc:virtuoso://localhost:1111";
//		VirtGraph graph = new VirtGraph ("CountryCodeEurostat", url, "dba", "fagiolo");

		File folder = new File("/home/link/wordnet-60c1995/full/");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        try {
				     Model md = VirtModel.openDatabaseModel("Wordnet3", url, "dba", "fagiolo");
				     md.read(new FileReader(file),null,"TURTLE");
				     System.out.println("==Inserted==");

				   } catch (Exception e) {
				     System.out.println("Exception=="+e);
				   }
		    }
		}
		
		
		
		
		
		
		// write it to standard out
//		model.write(System.out);
    }
	
	
	
	
}
