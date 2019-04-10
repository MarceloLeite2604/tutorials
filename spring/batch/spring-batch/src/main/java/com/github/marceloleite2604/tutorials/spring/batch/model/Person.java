package com.github.marceloleite2604.tutorials.spring.batch.model;

public class Person {

	private String lastName;
	private String firstName;

	public Person() {
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", firstName=" + firstName + "]";
	}

	

}