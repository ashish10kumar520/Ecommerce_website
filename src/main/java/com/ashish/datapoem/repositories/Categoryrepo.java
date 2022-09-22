package com.ashish.datapoem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.datapoem.Entity.Category;

public interface Categoryrepo extends JpaRepository<Category, Integer> {

}
