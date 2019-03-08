package org.pace.service.secondary;

import java.util.List;

import org.pace.model.Category;


public interface CategoryServiceSec {
	
	 Category findBycategoryId(int id);
	 
	 void saveCategory(Category category);
	 
	 void updateCategory(Category category);
	 
	 List<Category> findAllCategory();
}