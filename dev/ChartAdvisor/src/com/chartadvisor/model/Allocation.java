package com.chartadvisor.model;

import java.util.*;

import com.chartadvisor.utils.Combinations;

public class Allocation {
	
	public Allocation(){
		leftAllocations = null;
		rightAllocations = null;
	}
	public Allocation(ArrayList<String> left, ArrayList<String> right){
		leftAllocations = left;
		rightAllocations = right;
	}
	
	public ArrayList<String> leftAllocations, rightAllocations;
	
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
			for (String s : alloc.leftAllocations){
				if (!leftAllocations.contains(s))
						return false;
			}
			for (String s : alloc.rightAllocations){
				if (!rightAllocations.contains(s))
						return false;
			}
			return true;
		}
	}
	
}
