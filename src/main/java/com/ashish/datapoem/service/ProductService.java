package com.ashish.datapoem.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.datapoem.Entity.Product;
import com.ashish.datapoem.repositories.Productrepo;

@Service
public class ProductService {
	
	@Autowired
	private Productrepo productrepo;
	
	public List<Product> getAllProduct(){
		return productrepo.findAll();
	}
	
	public void addProduct(Product product) {
		productrepo.save(product);
	}
	
	public void removeProductById(Integer id) {
		productrepo.deleteById(id);
	}
	
	public Optional<Product> getProductById(Integer id){
		return productrepo.findById(id);
	}
	
	public List<Product> getAllProductsByCategoryId(Integer id){
		return productrepo.findAllByCategory_Id(id);
	}
}
