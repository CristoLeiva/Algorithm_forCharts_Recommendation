package com.chartadvisor.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.*;

public final class Chart {
	
private static final String chart = "chart.rdf";
	
	
	public static Model getModel(){
		return FileManager.get().loadModel(chart);
	}
	
	public static void main(String[] args) {
		Model m = Chart.getModel();
		
		//m.createResource("http://chartsMetaData.com/ColumnChart#").addProperty(RDFS.subClassOf,LOM).addProperty(DC.type, propertyType).addProperty(RDFS.label, "Column Chart");
		m.createResource("http://chartsMetaData.com/ColumnChart#").addProperty(RDFS.label, "Column Chart").addProperty(RDFS.isDefinedBy, m.createResource("ChartPattern1").addProperty(OWL2.annotatedSource, "ORDINAL").addProperty(OWL2.annotatedSource, "CATEGORICAL").addProperty(OWL2.annotatedTarget, "QUANTITATIVE"));
		try {
			m.write(new FileOutputStream(chart));
		} catch (FileNotFoundException e) {
			System.out.println("error saving model");
		}
	}

}
