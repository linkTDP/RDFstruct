package foo.dbgroup.RDFstruct;

public class LoadVoidOntology {

	public static void main( String[] args )
    {
		String a = "12965190^^http://www.w3.org/2001/XMLSchema#integer";
		
		a.replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#integer", "");
		
		System.out.println(a.replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#integer", ""));
		
    }
}
