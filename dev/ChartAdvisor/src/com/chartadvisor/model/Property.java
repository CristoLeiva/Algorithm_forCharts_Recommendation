package com.chartadvisor.model;

public class Property {
	
	private String propertyCompleteName;
	private String propertyName;
	private String propertyType;
	

	public String getPropertyCompleteName() {
		return propertyName;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public void setPropertyCompleteName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Property(String propertyName, String propertyType) {
		super();
		this.propertyName = propertyName;
		this.propertyType = propertyType;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Property))
			return false;
		Property p = (Property)o;
		if(p.getPropertyName().equalsIgnoreCase(propertyName))
			if(p.getPropertyType().equalsIgnoreCase(propertyType))
				return true;
		return false;
	}
	
	public String toString(){
		//return new String(propertyName+":"+propertyType);
		return new String(propertyName);
	}

}
