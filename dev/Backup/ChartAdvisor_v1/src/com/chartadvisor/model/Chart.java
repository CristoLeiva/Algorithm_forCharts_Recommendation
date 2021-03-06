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

private static final String[] geoLabels = {"country","city","region","zip","zipcode", "capital"};

private static final String[] coordinates = {"lat", "mag", "latitude", "magnitude"}; 
	
	
	public static Model getModel(){
		FileManager.get().addLocatorClassLoader(Chart.class.getClassLoader());
		return FileManager.get().loadModel(chart);
	}
	
	// Transform a StmtIterator to an allocation, in order to compare the chart to the generated allocations
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
	
	// Search for charts, that can support the given allocation:
	// allocation: is the allocation desired in LOM form
	// prettyPrint: is the same allocation with properties Names intead of LOMs
	private static List<String[]> findPossibleCharts(String[] propertiesShortNames, Allocation allocation, Allocation prettyPrint, int totalProperties){
		List<String[]> result = new ArrayList<String[]>();
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
					
					if(alloc.equals(allocation)){ //There is a match!, this particular chart can visualize this particular allocation
						String[] suggestedChart = new String[]{"","",""};
						suggestedChart[0] = resource.getProperty(RDFS.label).getString();
						suggestedChart[1] = 100*allocation.getLength()/totalProperties + "";
						suggestedChart[2] = prettyPrint.toString();
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
							else{
								if((suggestedChart[0].equals("Pie Chart"))||(suggestedChart[0].equals("Donut Chart"))){
									if(possiblePieDonutChart(propertiesShortNames, allocation))
										result.add(suggestedChart);
								}
								else
									result.add(suggestedChart);
							}
						}
					}
					//System.out.println(stmtIter.next().getProperty(OWL2.annotatedSource).getString());
				}catch (Exception e){
					
				}
				
			}
			
		}
		return result;
	}
	
	// an allocation should contain at least one of the properties in geoLabels[] in order to allow a geo chart suggestion
	private static boolean possibleGeoChart(String[] propertiesShortNames, Allocation allocation) {
		for(String property: propertiesShortNames){
			for(String gLabel : geoLabels){
				if(property.toLowerCase().contains(gLabel))
					return true;
			}
		}
		
		return false;
	}
	
	// an allocation should not contain any of the properties in coordinates[] in order to allow a pie chart suggestion
	private static boolean possiblePieDonutChart(String[] propertiesShortNames, Allocation allocation) {
		for(String property: propertiesShortNames){
			for(String gLabel : coordinates){
				if(property.toLowerCase().contains(gLabel))
					return false;
			}
		}
		
		return true;
	}
	
	// Find the charts that can visualize the list of properties
	// path: is the path to the input data file.
	public static List<String[]> findCharts(String[] propertiesShortNames, String path){
		ArrayList<String[]> charts = new ArrayList<String[]>();
		List<String> propertiesSet = new ArrayList<String>(Arrays.asList(propertiesShortNames));
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
		
		//Generating all possible allocations out of the list of given properties
		List<Allocation> allocations = AllocationGenerator.generateAllocations(properties);
		
		// Query the data file for all those properties
		List<String[]> propertiesValues = DataSets.sparql_query_property(DataSets.create_model(path), propertiesCompleteNames);
		
		// Validate generated properties against the resultSet of executing the query.
		allocations = AllocationGenerator.validateAllocations(allocations,propertiesValues, propertiesShortNames);
		if(allocations.size()==0)
			System.out.println("There are now valid allocations for these properties..");
		System.out.println("\nPossible Valid Allocations:\n"+allocations+"\n");
		
		
		//System.out.println(findCharts(Allocation.toLOMAllocation(new Allocation(left, right))));
		
		for(Allocation alloc : allocations){
			// Generate the LOM allocation for every valid generated allocation
			Allocation allocLOM = Allocation.toLOMAllocation(alloc);
			//Search for possible charts to visualize each and particular allocation
			List<String[]> foundcharts = findPossibleCharts(propertiesShortNames, allocLOM, alloc, propertiesShortNames.length);
			if(foundcharts.size()==0){
//				System.out.print("No charts found for allocation: ");
//				System.out.println(alloc);
//				System.out.println("-------------------");
			}
			else{
				charts.addAll(foundcharts);
				for(String[] chart:charts){
//					System.out.print("Chart: "+chart[0]);
//					System.out.print(" can be used to visualize allocation: "+ alloc);
//					System.out.println(" with percentage: "+chart[1] + "%");
//					System.out.println("-------------------");
				}
			}
		}
		
		// sometimes, 2+ allocations can be visualized with the same chart, resulting in repeated suggestion
		// removing duplicates here:
		removeDuplicates(charts);
		
		//Sort suggested chart by accuracy:
		Collections.sort(charts, new Comparator<String[]>() {
			@Override
	        public int compare(String[] o1, String[] o2) {
	        	return (Integer.parseInt(o2[1]) >= Integer.parseInt(o1[1])) ? 1 : -1;
	        }});
		
		return charts;
	}

	
	private static void removeDuplicates(
			ArrayList<String[]> charts) {
		List<String[]> duplicates = new ArrayList<String[]>();
		for(String[] chart1 : charts){
			int encountered = 0;
			for(String[] chart2 : charts){
				if(chart1[0].equals(chart2[0])){ // if the same chart name
					if(Integer.parseInt(chart1[1])>Integer.parseInt(chart2[1])){ // if the accuracy of chart2 is less, remove it
						duplicates.add(chart2);
					}
					else{
						if(Integer.parseInt(chart1[1])<Integer.parseInt(chart2[1])){  // if the accuracy of chart1 is less, remove it
							duplicates.add(chart1);
						}
						else{
							encountered++;
							if(encountered>1)
								duplicates.add(chart2);
						}
					}
				}
			}
		}
		charts.removeAll(duplicates);
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
		String[] propertiesNames = {"nameShort", "populationTotal", "populationYear", "HDIYear"};
		//String path = "/home/ahmad/Documents/geodata.rdf";
		String path = "/home/ahmad/git/Algorithm_forCharts_Recommendation/dev/Application/Bin/WebSemanticsLab/src/main/geodata.rdf";

		
		List<String[]> charts = findCharts(propertiesNames, path);
		for(String[] chart : charts){
			System.out.print("Chart: "+chart[0]);
			System.out.print(" can be used to visualize allocation: "+ chart[2]);
			System.out.println(" with percentage: "+chart[1] + "%");
			System.out.println("-------------------");
		}
	}

}
