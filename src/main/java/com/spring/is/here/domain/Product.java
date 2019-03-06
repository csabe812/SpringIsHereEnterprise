/**
 * COPYRIGHTS AND OTHER STUFFS GOES HERE
 */
package com.spring.is.here.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This is a Product POJO (Plain Old Java Object)
 * 
 * @author Csabcsi
 *
 */
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int price;
	private String description;
	@ManyToOne
	private Shop shop;
	
	/**
	 * Default parameterless constructor
	 */
	private Product() {	}

	
	
	public Product(String name, int price, String description, Shop shop) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.shop = shop;
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
	 * Id of the product
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setting the id of the product
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
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
