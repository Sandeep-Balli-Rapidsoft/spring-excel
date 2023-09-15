package com.dao;

import java.util.List;

import com.entity.Product;

public interface ProductDao {
	
	public Product save(Product product);
	
	public List<Product> getAll();
}
