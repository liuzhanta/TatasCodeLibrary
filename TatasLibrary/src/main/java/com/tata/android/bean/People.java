package com.tata.android.bean;

public class People implements BaseModel{
	private String name;

	public String getName() {
		return name;
	}

	public People(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
