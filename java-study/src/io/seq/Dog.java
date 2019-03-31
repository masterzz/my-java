package io.seq;

import java.io.Serializable;

public class Dog implements Serializable{
	private static final long serialVersionUID = 130874992691377104L;
	private String name;
	private int age;
	
	private String color ;
	
	//伙伴
	private Cat partner ;
	
	//临时的
	private transient Person owner ;
	
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Cat getPartner() {
		return partner;
	}

	public void setPartner(Cat partner) {
		this.partner = partner;
	}

	public Dog(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("kkkk");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}