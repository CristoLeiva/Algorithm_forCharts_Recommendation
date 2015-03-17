package main;

import java.lang.reflect.Array;
import java.util.*;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;


public class DataSets {

	public static void main(String[] args) {


	FileManager.get().addLocatorClassLoader(DataSets.class.getClassLoader());
	Model model	= FileManager.get().loadModel("K:/9- Master in Computer Sciences/Fourth Semester/Lab Web Semantics/New folder/WebSemanticsLab/src/main/geodata.rdf");

	StmtIterator iter = model.listStatements();
    Set<String> setProperties 	= new HashSet<String>();
   
	while (iter.hasNext()) {
	    Statement stmt      = iter.nextStatement();  // get next statement
	    Property  predicate = stmt.getPredicate();   // get the predicate
	    RDFNode   object    = stmt.getObject();      // get the object

	    String Datatype = get_datatype(object).toString();
	    
	    if(!(object instanceof Resource)&&(Datatype != "")&&(Datatype.contains("#")&&!(Datatype.contains("@")))){

	    	setProperties.add(predicate.getLocalName()+"#"+Datatype.substring(Datatype.lastIndexOf("#")+1));
				    	
	    }
	    
	}
	
	System.out.println(setProperties.toString());
	
	}
	
	public static String get_datatype(RDFNode object){
		String Output = object.asNode().toString();
		return Output;
	}
	
	static void sparql_query(Model model,String StringQuery){
		
		
		Query query = QueryFactory.create(StringQuery);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		
		
		try{
			
			ResultSet results = qexec.execSelect();

			while( results.hasNext()){
				
				QuerySolution slon = results.nextSolution();
				Literal name =slon.getLiteral("x");
				Literal family_name =slon.getLiteral("y");
	
				System.out.println(name);
				
				System.out.println(family_name);

				
			} 
			
			
		}finally
		{
			qexec.close();
		}
		
	}

}
