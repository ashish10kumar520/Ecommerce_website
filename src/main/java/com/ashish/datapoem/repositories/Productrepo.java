package com.ashish.datapoem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.datapoem.Entity.Product;

public interface Productrepo extends JpaRepository<Product, Integer> {
	List<Product> findAllByCategory_Id(Integer id);
}
