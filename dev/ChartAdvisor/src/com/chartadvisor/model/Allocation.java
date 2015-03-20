package com.chartadvisor.model;

import java.util.*;

import com.chartadvisor.utils.Combinations;

public class Allocation {
	
	public Allocation(){
		leftAllocations = null;
		rightAllocations = null;
	}
	public Allocation(ArrayList<Property> left, ArrayList<Property> right){
		leftAllocations = left;
		rightAllocations = right;
	}
	
	public ArrayList<Property> leftAllocations, rightAllocations;
	
	public String toString(){
		String printed = "(";
		printed += Combinations.toStringList(leftAllocations);
		printed	+=")---> (";
		printed += Combinations.toStringList(rightAllocations);
		printed += ")";
		//System.out.println();
		return printed;
		
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Allocation))
			return false;
		else{
			Allocation alloc = (Allocation)o;
			for (Property s : alloc.leftAllocations){
				if (!leftAllocations.contains(s))
						return false;
			}
			for (Property s : alloc.rightAllocations){
				if (!rightAllocations.contains(s))
						return false;
			}
			return true;
		}
	}
	
	
	public static Allocation toLOMAllocation(Allocation alloc){
		ArrayList<Property> left = new ArrayList<Property>();
		ArrayList<Property> right = new ArrayList<Property>();
		for (Property s : alloc.leftAllocations){
			String LOM = Dictionary.getLOM(s.getPropertyName(), s.getPropertyType());
			left.add(new Property("",LOM));
		}
		for (Property s : alloc.rightAllocations){
			String LOM = Dictionary.getLOM(s.getPropertyName(), s.getPropertyType());
			right.add(new Property("",LOM));
		}
		
		return new Allocation(left, right);
	}
	
}
