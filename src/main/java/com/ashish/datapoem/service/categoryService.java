package com.ashish.datapoem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.datapoem.Entity.Category;
import com.ashish.datapoem.repositories.Categoryrepo;


@Service
public class categoryService {

	@Autowired
	private Categoryrepo categoryrepo;
	
	public List<Category> getAllCategory(){
		return categoryrepo.findAll();
	}
	
	public void addCategory(Category category) {
		categoryrepo.save(category);
	}
	
	public void removeCategoryByid(Integer id) {
		categoryrepo.deleteById(id);
	}
	
	public Optional<Category> getCategoryById(Integer id){
		return categoryrepo.findById(id);
	}
	
}
