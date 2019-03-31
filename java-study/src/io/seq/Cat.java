package io.seq;

import java.io.Serializable;

public class Cat implements Serializable{
	private static final long serialVersionUID = 718380935795444836L;
	private String name;
	
	private Person owner ;
	
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Cat(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}