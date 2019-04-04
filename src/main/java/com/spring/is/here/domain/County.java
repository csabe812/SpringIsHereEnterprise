package com.spring.is.here.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "counties")
public class County {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String countyName;
	@OneToMany(mappedBy = "countyCategory", cascade = CascadeType.ALL)
	private Set<Product> product;

	public County() {
	}

	public County(long id, String countyName, Set<Product> product) {
		this.id = id;
		this.countyName = countyName;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "County [id=" + id + ", countyName=" + countyName + ", product=" + product + "]";
	}

}
