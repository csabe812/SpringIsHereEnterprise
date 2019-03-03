package com.spring.is.here;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="person")
@Component
public class Person {

	private int age;
	private String name;
	private String address;

	public Person() {
	}

	public Person(int age, String name, String address) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", address=" + address + "]";
	}

}
