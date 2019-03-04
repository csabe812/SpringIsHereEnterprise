/**
 * COPYRIGHTS AND OTHER STUFFS GOES HERE
 */
package com.spring.is.here.domain;

/**
 * This is a Product POJO (Plain Old Java Object)
 * 
 * @author Csabcsi
 *
 */
public class Product {

	private String name;
	private int price;
	private String description;

	/**
	 * Default parameterless constructor
	 */
	public Product() {

	}

	/**
	 * Constructor with parameters
	 * 
	 * @param name
	 * @param price
	 * @param description
	 */
	public Product(String name, int price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}

	/**
	 * Name of the product
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setting the name of the product
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The price of the product
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Setting the price of the product
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * The description of the product
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setting the description of the product
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * A toString() method to print the object's data
	 */
	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", description=" + description + "]";
	}

}
