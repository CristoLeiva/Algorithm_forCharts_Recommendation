package com.chartadvisor.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class OutputManager {
	
	public void generateRDFOutput(List<String[]> results, File file){
		try {
			System.out.println(System.getProperty("user.dir"));
			StringBuilder resultText = new StringBuilder(new Scanner(new File(System.getProperty("user.dir")+"/src/com/chartadvisor/controller/recommendation.rdf")).useDelimiter("\\Z").next());
			int counter = 1;
			for (String[] temp : results) {
				replaceStr(resultText, "chart"+counter+".0", temp[0]);
				replaceStr(resultText, "chart"+counter+".1", temp[0]);
				replaceStr(resultText, "chart"+counter+".2", ""+counter);
				replaceStr(resultText, "chart"+counter+".3", temp[1]);
				counter++;
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(resultText.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void replaceStr(StringBuilder builder, String from, String to)
	{
	    int index = builder.indexOf(from);
	    while (index != -1)
	    {
	        builder.replace(index, index + from.length(), to);
	        index += to.length(); // Move to the end of the replacement
	        index = builder.indexOf(from, index);
	    }
	}
}
