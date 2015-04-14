package com.chartadvisor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chartadvisor.model.Allocation;
import com.chartadvisor.model.Property;
import com.chartadvisor.utils.Combinations;

public class AllocationGenerator {
	
	//Get the index of a property in the list of properties
	public static int getPropertyIndex(String[] properties, String property){
		for(int i=0; i<properties.length; i++){
			if(properties[i].equals(property)){
				return i;
			}
		}
		return -1;
	}
	
	//Get the indeces of the list of given properties, within the given allocation
	public static int[] getIndecesArray(Allocation alloc, String[] properties){
		int[] result = new int[alloc.getLength()];
		for(int i=0; i<alloc.leftAllocations.size(); i++){
			result[i] = getPropertyIndex(properties, alloc.leftAllocations.get(i).getPropertyName());
		}
		for(int i=0; i<alloc.rightAllocations.size(); i++){
			result[i+alloc.leftAllocations.size()] = getPropertyIndex(properties, alloc.rightAllocations.get(i).getPropertyName());
		}
		return result;
	}
	
	//Reduce the "whole" array to a smaller array that contains only properties in "indeces"
	public static List<String[]> getPartialArray(List<String[]> whole, int[] indeces){
		List<String[]> partial = new ArrayList<String[]>();
		for(int i=0; i<whole.size();i++){
			String[] part = new String[indeces.length];
			for(int j=0; j<whole.get(i).length;j++){
				for(int k=0; k<indeces.length; k++){
					part[k]=whole.get(i)[indeces[k]];
				}
			}
			partial.add(part);
		}
		return partial;
	}
	
	//Generate all possible allocations from a list of properties
	public static List<Allocation> generateAllocations(Property[] properties) throws NullPointerException{
		ArrayList<Allocation> allocations = new ArrayList<Allocation>();
		ArrayList<ArrayList<Property>> list1,list2;
		for (int i=1; i<=properties.length; i++){
			list1 = Combinations.combination(properties, i);
			for(int j=0; j<list1.size(); j++){
				for(int k=1; k<= properties.length-list1.get(j).size(); k++){
					Object[] newProp = removeListFromList(properties, list1.get(j));
					list2 = Combinations.combination(newProp, k);
					
					for(int l=0; l<list2.size();l++){
						Allocation alloc = new Allocation(list1.get(j), list2.get(l));
						if(!allocations.contains(alloc))
							allocations.add(alloc);
					}
				}
			}
		}
		return allocations;
	}
	
	public static Object[] removeListFromList(Property[] properties, ArrayList<Property> combinations){
		ArrayList<Property> result = new ArrayList<Property>(); 
		for(int i=0; i<properties.length; i++){
			if(!combinations.contains(properties[i]))
				result.add(properties[i]);
		}
		return result.toArray();
	}
	
	//Get the valid allocations from  list of all generated allocations:
	// Valid : 1- RightUnique, 2-LeftTotal		
	public static List<Allocation> validateAllocations (List<Allocation> allocations, List<String[]> values, String[] properties){
		return getRightUniqueAllocations(getLefTotalAllocations(allocations, values, properties), values, properties);
	}
	
	//Get all RightUnique Allocations
	public static List<Allocation> getRightUniqueAllocations (List<Allocation> allocations, List<String[]> values, String[] properties){
		List<Allocation> rightUnique = new ArrayList<Allocation>();
		for(Allocation alloc : allocations){
			if(isRightUnique(alloc, values, properties))
				rightUnique.add(alloc);
		}
		return rightUnique;
	}
	
	//Check if the allocation is RightUnique, which means
	// if xRy and xRz --> y	= z
	public static boolean isRightUnique(Allocation alloc, List<String[]> values, String[] properties){
		int[] indeces = getIndecesArray(alloc, properties);
		List<String[]> partialValues = getPartialArray(values, indeces);
		for(String[] resultRow : partialValues){
			if(similarInputDifferentOutput(resultRow, partialValues, alloc.leftAllocations.size()))
				return false;
		}
		return true;
	}
	
	// Check if a list of properties i.e. "input" (should be on the leftSide of an allocation)
	// more than one detination in the rightSide of the allocation
	private static boolean similarInputDifferentOutput(String[] row1,
			List<String[]> partialValues, int size) {
		boolean leftSimilar = true;
		boolean rightSimilar = true;
		for(String[] row2 : partialValues){
			leftSimilar = true;
			for(int i=0;i<size; i++){
				if((row1[i]!=null)&&(row2[i]!=null))
					if(!row1[i].equalsIgnoreCase(row2[i])){
						leftSimilar = false;
						break;
					}
			}
			if(leftSimilar){
				rightSimilar = true;
				for(int i=size; i<row1.length; i++){
					if((row1[i]!=null)&&(row2[i]!=null))
						if(!row1[i].equalsIgnoreCase(row2[i])){
							rightSimilar = false;
							break;
						}
				}
				if(!rightSimilar)
					return true;
			}
		}
		return false;
	}

	//Get all leftTotal Allocations
	public static List<Allocation> getLefTotalAllocations (List<Allocation> allocations, List<String[]> values, String[] properties){
		List<Allocation> leftTotal = new ArrayList<Allocation>();
		for(Allocation alloc : allocations){
			if(isLeftTotal(alloc, values, properties))
				leftTotal.add(alloc);
		}
		return leftTotal;
	}
	

	//Check of the allocation is leftTotal
	//Every element on the left, shall have a destination element
	private static boolean isLeftTotal(Allocation alloc, List<String[]> values,
			String[] properties) {
		int[] indeces = getIndecesArray(alloc, properties);
		List<String[]> partialValues = getPartialArray(values, indeces);
		for(String[] resultRow : partialValues){
			for(int j=alloc.leftAllocations.size(); j<resultRow.length; j++){
				if(resultRow[j]==null)
					return false;
				if(resultRow[j].length()==0)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		//String[] properties = {"Country", "Population", "GDP", "Year"};
		Property[] properties = {new Property("Country","STRING"),new Property("GDP","INTEGER"),new Property("Population","INTEGER"), new Property("Year","INTEGER") };
		List<Allocation> allocations = generateAllocations(properties);
		for (Allocation allocation : allocations){
			System.out.println(allocation);
		}
	}

}
