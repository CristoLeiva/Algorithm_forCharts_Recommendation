package com.chartadvisor.model;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.*;

import java.util.*;

public final class Chart {
	
private static final String chart = "/home/ahmad/Documents/chart.rdf";
	
	
	public static Model getModel(){
		FileManager.get().addLocatorClassLoader(Chart.class.getClassLoader());
		return FileManager.get().loadModel(chart);
	}
	
	public static Allocation toAllocation(StmtIterator stmtIter){
		ArrayList<String> left = new ArrayList<String>();
		ArrayList<String> right = new ArrayList<String>();
		while (stmtIter.hasNext()){
			Statement stmt = stmtIter.next();
			if(stmt.getPredicate().getLocalName().equals("annotatedTarget"))
				right.add(stmt.getString());
			else if (stmt.getPredicate().getLocalName().equals("annotatedSource"))
				left.add(stmt.getString());
		}
		
		return new Allocation(left, right);
	}
	
	public static ArrayList<String> findCharts(Allocation allocation){
		ArrayList<String> result = new ArrayList<String>();
		Model m = getModel();
		ResIterator resources = m.listResourcesWithProperty(RDFS.label);
		while(resources.hasNext()){
			Resource resource = resources.next();
			StmtIterator stmtIter =  resource.listProperties();
			while (stmtIter.hasNext()){
				try{
					Resource resource2 = stmtIter.next().getResource();
					StmtIterator stmtIter2 =  resource2.listProperties();
					Allocation alloc = toAllocation(stmtIter2);
					//System.out.println(alloc);
					if(alloc.equals(allocation))
						result.add(resource.getProperty(RDFS.label).getString());
					//System.out.println(stmtIter.next().getProperty(OWL2.annotatedSource).getString());
				}catch (Exception e){
					
				}
				
			}
			
		}
		return result;
	}
	
	public static void main(String[] args) {
//		Model m = ModelFactory.createDefaultModel();//Chart.getModel();
//		m.createResource("http://chartsmetadata.com/ColumnChart").addProperty(RDFS.label, "Column Chart").addProperty(RDFS.isDefinedBy, m.createResource("http://chartsmetadata.com/ColumnChart/ChartPattern1").addProperty(OWL2.annotatedSource, "ORDINAL").addProperty(OWL2.annotatedSource, "CATEGORICAL").addProperty(OWL2.annotatedTarget, "QUANTITATIVE")).addProperty(RDFS.isDefinedBy, m.createResource("http://chartsmetadata.com/ColumnChart/ChartPattern2").addProperty(OWL2.annotatedSource, "ORDINAL").addProperty(OWL2.annotatedTarget, "QUANTITATIVE_"));
//		try {
//			m.write(new FileOutputStream(chart));
//		} catch (FileNotFoundException e) {
//			System.out.println("error saving model");
//		}
		ArrayList<String> right = new ArrayList<String>();
		right.add("QUANTITATIVE");
		ArrayList<String> left = new ArrayList<String>();
		left.add("CATEGORICAL");
		left.add("ORDINAL");
		//findCharts(new Allocation(left, right));
		System.out.println(findCharts(new Allocation(left, right)));
	}

}
