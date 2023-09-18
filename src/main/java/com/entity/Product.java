package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	public Integer id;

	public String name;

	public double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

}
