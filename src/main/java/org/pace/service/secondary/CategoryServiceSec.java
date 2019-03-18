package org.pace.service.secondary;

import java.util.List;

import org.pace.model.Category;


public interface CategoryServiceSec {
	
	 Category findBycategoryId(int id);
	 
	 void save(Category data);
	 
	 void update(Category data);
	 
	 List<Category> findAll();
}