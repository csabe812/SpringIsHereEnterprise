package com.spring.is.here.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * Shop entity for storing shops
 * 
 * @author csabe812
 *
 */
@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@JsonBackReference
	@OneToMany(mappedBy = "shop")
	private List<Product> products;
	@ManyToOne
	private User shopOwner;

	/**
	 * Default constuctor
	 */
	private Shop() {
	}

	/**
	 * Constuctor with parameters
	 */
	public Shop(String name) {
		super();
		this.name = name;
	}

	/**
	 * ID getter
	 */
	public Long getId() {
		return id;
	}

	/**
	 * ID setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Name getter
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Products getter
	 * 
	 * @return
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Products setter
	 * 
	 * @param products
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
	public User getShopOwner() {
		return shopOwner;
	}

	public void setShopOwner(User shopOwner) {
		this.shopOwner = shopOwner;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", products=" + products + "]";
	}

	
}
