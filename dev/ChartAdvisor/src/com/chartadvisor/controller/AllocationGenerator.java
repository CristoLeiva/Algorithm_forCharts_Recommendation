package com.chartadvisor.controller;

import java.util.ArrayList;

import com.chartadvisor.model.Allocation;
import com.chartadvisor.model.Property;
import com.chartadvisor.utils.Combinations;

public class AllocationGenerator {
	
//	public static ArrayList<Allocation> generateAllocations(String[] properties){
//		ArrayList<Allocation> allocations = new ArrayList<Allocation>();
//		ArrayList<ArrayList<String>> list1,list2;
//		for (int i=1; i<=properties.length; i++){
//			list1 = Combinations.combination(properties, i);
//			for(int j=0; j<list1.size(); j++){
//				for(int k=1; k<= properties.length-list1.get(j).size(); k++){
//					Object[] newProp = removeListFromList(properties, list1.get(j));
//					list2 = Combinations.combination(newProp, k);
//					for(int l=0; l<list2.size();l++){
//						allocations.add(new Allocation(list1.get(j), list2.get(l)));
//					}
//				}
//			}
//		}
//		return allocations;
//	}
	
	public static ArrayList<Allocation> generateAllocations(Property[] properties){
		ArrayList<Allocation> allocations = new ArrayList<Allocation>();
		ArrayList<ArrayList<Property>> list1,list2;
		for (int i=1; i<=properties.length; i++){
			list1 = Combinations.combination(properties, i);
			for(int j=0; j<list1.size(); j++){
				for(int k=1; k<= properties.length-list1.get(j).size(); k++){
					if(k==3)
						System.out.print("mmm");
					Object[] newProp = removeListFromList(properties, list1.get(j));
					list2 = Combinations.combination(newProp, k);
					
					for(int l=0; l<list2.size();l++){
						allocations.add(new Allocation(list1.get(j), list2.get(l)));
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
	
//	public static Object[] removeListFromList(String[] properties, ArrayList<String> combinations){
//		ArrayList<String> result = new ArrayList<String>(); 
//		for(int i=0; i<properties.length; i++){
//			if(!combinations.contains(properties[i]))
//				result.add(properties[i]);
//		}
//		return result.toArray();
//	}
	
	public static void main(String[] args){
		//String[] properties = {"Country", "Population", "GDP", "Year"};
		Property[] properties = {new Property("Country","STRING"),new Property("GDP","INTEGER"),new Property("Population","INTEGER"), new Property("Year","INTEGER") };
		ArrayList<Allocation> allocations = generateAllocations(properties);
		for (Allocation allocation : allocations){
			System.out.println(allocation);
		}
	}

}
