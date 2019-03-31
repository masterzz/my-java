package io.seq;

import java.io.Serializable;

/**
 * Person
 */
public class Person implements Serializable{
	private static final long serialVersionUID = -7629715871683829614L;
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
