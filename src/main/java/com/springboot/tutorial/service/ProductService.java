package com.springboot.tutorial.service;

import java.util.Collection;
import com.springboot.tutorial.entity.Product;


public interface ProductService {

	public abstract void createProduct(Product product);

	public abstract void updateProduct(String id, Product product);

	public abstract void deleteProduct(String id);

	public abstract Collection<Product> getProducts();
	
	public abstract Product getProductById(String id);

}
