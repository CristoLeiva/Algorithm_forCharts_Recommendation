package com.chartadvisor.model;

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
	
	public static String[] getProperties(String URL) {
		FileManager.get().addLocatorClassLoader(DataSets.class.getClassLoader());
		Model model	= FileManager.get().loadModel(URL);
		
		StmtIterator iter = model.listStatements();
	    Set<String> setProperties = new HashSet<String>();
	
		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
		    Statement stmt      = iter.nextStatement();  // get next statement
		    Resource  subject   = stmt.getSubject();     // get the subject
		    Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object

		    //System.out.print(subject.getLocalName());
		    //System.out.print(" " + predicate.getLocalName()+ " ");
		    
		    setProperties.add(predicate.getLocalName());
		    
		    if (object instanceof Resource) {
		       //System.out.print(((Resource) object).getLocalName());
			    } else {
			        // object is a literal
			        //System.out.print(" \"" + object.toString() + "\"");
			    }
		    //System.out.println(" .");
		}
		System.out.println(setProperties.toString());
		
		return setProperties.toArray(new String[setProperties.size()]);
	}

	public static void main(String[] args) {

		FileManager.get().addLocatorClassLoader(DataSets.class.getClassLoader());
		Model model	= FileManager.get().loadModel("C:/Users/Cristo/Desktop/LAB SEMANTIC/websemanticslab/src/main/worldbank-slice-5000.rdf");

//		model.write(System.out,"RDF/JSON");
//		String StringQuery = 
//				"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
//				"PREFIX foaf:<http://xmlns.com/foaf/0.1/>" +
//				"SELECT *"	+
//				"WHERE {"	+
//				"?person foaf:name ?x."	+
//				"?person foaf:family_name ?y."	+
//				"}";
// 		sparql_query(model,StringQuery);
	
		StmtIterator iter = model.listStatements();
	    Set<String> setProperties = new HashSet<String>();
	
		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
		    Statement stmt      = iter.nextStatement();  // get next statement
		    Resource  subject   = stmt.getSubject();     // get the subject
		    Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object

		    //System.out.print(subject.getLocalName());
		    //System.out.print(" " + predicate.getLocalName()+ " ");
		    
		    setProperties.add(predicate.getLocalName());
		    
		    if (object instanceof Resource) {
		       //System.out.print(((Resource) object).getLocalName());
			    } else {
			        // object is a literal
			        //System.out.print(" \"" + object.toString() + "\"");
			    }
		    //System.out.println(" .");
	
		}
		
		System.out.println(setProperties.toString());
	
	}
	
	public static String get_datatype(RDFNode object){
		String Output = object.asNode().toString();
		return Output;
	}
	
	public static void sparql_query(Model model,String StringQuery){
		
		Query query = QueryFactory.create(StringQuery);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		
		try{
			
			ResultSet results = qexec.execSelect();

			while( results.hasNext()){
				
				QuerySolution slon = results.nextSolution();
				Literal name =slon.getLiteral("x");
				Literal family_name =slon.getLiteral("y");
	
				//System.out.println(name);
				
				//System.out.println(family_name);
			} 
			
		}finally
		{
			qexec.close();
		}
		
	}

}
