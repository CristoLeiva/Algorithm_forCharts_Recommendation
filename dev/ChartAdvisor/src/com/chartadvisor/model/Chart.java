package com.chartadvisor.model;

import com.chartadvisor.controller.AllocationGenerator;
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

private static final String[] geoLabels = {"country","city","region","zip","zipcode"};
	
	
	public static Model getModel(){
		FileManager.get().addLocatorClassLoader(Chart.class.getClassLoader());
		return FileManager.get().loadModel(chart);
	}
	
	public static Allocation toAllocation(StmtIterator stmtIter){
		ArrayList<Property> left = new ArrayList<Property>();
		ArrayList<Property> right = new ArrayList<Property>();
		while (stmtIter.hasNext()){
			Statement stmt = stmtIter.next();
			if(stmt.getPredicate().getLocalName().equals("annotatedTarget"))
				right.add(new Property("",stmt.getString()));
			else if (stmt.getPredicate().getLocalName().equals("annotatedSource"))
				left.add(new Property("",stmt.getString()));
		}
		
		return new Allocation(left, right);
	}
	
	private static ArrayList<String[]> findPossibleCharts(String[] propertiesShortNames, Allocation allocation, int totalProperties){
		ArrayList<String[]> result = new ArrayList<String[]>();
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
					//System.out.println("\nTO ALLOCATION:\n"+alloc);
					if(alloc.equals(allocation)){
						String[] suggestedChart = new String[]{"",""};
						suggestedChart[0] = resource.getProperty(RDFS.label).getString();
						suggestedChart[1] = 100*allocation.getLength()/totalProperties + "";
						boolean add = true;
						for(String[] existingResult:result){
							if(existingResult[0].equals(suggestedChart[0]))
								if(existingResult[1].equals(suggestedChart[1]))
									add = false;
						}
						if(add){
							if(suggestedChart[0].equals("Geo Chart")){
								if(possibleGeoChart(propertiesShortNames, allocation))
									result.add(suggestedChart);
							}
							else	
								result.add(suggestedChart);
						}
					}
					//System.out.println(stmtIter.next().getProperty(OWL2.annotatedSource).getString());
				}catch (Exception e){
					
				}
				
			}
			
		}
		return result;
	}
	
	private static boolean possibleGeoChart(String[] propertiesShortNames, Allocation allocation) {
		for(String property: propertiesShortNames){
			for(String gLabel : geoLabels){
				if(property.toLowerCase().contains(gLabel))
					return true;
			}
		}
		
		return false;
	}

	public static List<String[]> findCharts(String[] propertiesShortNames, String path){
		ArrayList<String[]> charts = new ArrayList<String[]>();
		List<String> propertiesSet = new ArrayList<String>(Arrays.asList(propertiesShortNames));
		//Property[] properties = {new Property("pop_count","integer"),new Property("year","integer"),new Property("country","string")};
		List<String[]> literals = DataSets.get_properties(DataSets.create_model(path), 1);
		Property[] properties = new Property[propertiesShortNames.length];
		String[] propertiesCompleteNames = new String[propertiesShortNames.length];
		for(String[] literal : literals){
			if(propertiesSet.contains(literal[2])){
				int index = propertiesSet.indexOf(literal[2]);
				properties[index] = new Property(literal[1], literal[2], literal[4]);
				propertiesCompleteNames[index] = "<"+literal[1]+">";
				System.out.println(literal[1]);
			}
		}
		//findCharts(new Allocation(left, right));
		List<Allocation> allocations = AllocationGenerator.generateAllocations(properties);
		
		List<String[]> propertiesValues = DataSets.sparql_query_property(DataSets.create_model(path), propertiesCompleteNames);
		
		allocations = AllocationGenerator.validateAllocations(allocations,propertiesValues, propertiesShortNames);
		if(allocations.size()==0)
			System.out.println("There are now valid allocations for these properties..");
		System.out.println("\nPossible Valid Allocations:\n"+allocations+"\n");
		
		
//		for (String[] values : propertiesValues){
//			for(String value : values){
//				System.out.print(value+"\t");
//			}
//			System.out.println();
//		}
		
		//System.out.println(findCharts(Allocation.toLOMAllocation(new Allocation(left, right))));
		for(Allocation alloc : allocations){
			Allocation allocLOM = Allocation.toLOMAllocation(alloc);
			ArrayList<String[]> foundcharts = findPossibleCharts(propertiesShortNames, allocLOM, propertiesShortNames.length);
			if(foundcharts.size()==0){
//				System.out.print("No charts found for allocation: ");
//				System.out.println(alloc);
//				System.out.println("-------------------");
			}
			else{
				charts.addAll(foundcharts);
				for(String[] chart:charts){
					System.out.print("Chart: "+chart[0]);
					System.out.print(" can be used to visualize allocation: "+ alloc);
					System.out.println(" with percentage: "+chart[1] + "%");
					System.out.println("-------------------");
				}
			}
		}
		return charts;
	}
	
	public static void main(String[] args) {
//		Model m = ModelFactory.createDefaultModel();//Chart.getModel();
//		m.createResource("http://chartsmetadata.com/ColumnChart").addProperty(RDFS.label, "Column Chart").addProperty(RDFS.isDefinedBy, m.createResource("http://chartsmetadata.com/ColumnChart/ChartPattern1").addProperty(OWL2.annotatedSource, "ORDINAL").addProperty(OWL2.annotatedSource, "CATEGORICAL").addProperty(OWL2.annotatedTarget, "QUANTITATIVE")).addProperty(RDFS.isDefinedBy, m.createResource("http://chartsmetadata.com/ColumnChart/ChartPattern2").addProperty(OWL2.annotatedSource, "ORDINAL").addProperty(OWL2.annotatedTarget, "QUANTITATIVE_"));
//		try {
//			m.write(new FileOutputStream(chart));
//		} catch (FileNotFoundException e) {
//			System.out.println("error saving model");
//		}
//		ArrayList<Property> right = new ArrayList<Property>();
//		right.add(new Property("pop_count","integer"));
//		ArrayList<Property> left = new ArrayList<Property>();
//		left.add(new Property("year","integer"));
//		left.add(new Property("country","string"));
		
		
		//To be provided by the interface
		String[] propertiesNames = {"nameShort", "populationTotal", "populationYear", "HDITotal"};
		String path = "/home/ahmad/Documents/geodata.rdf";
		
		findCharts(propertiesNames, path);
	}

}
