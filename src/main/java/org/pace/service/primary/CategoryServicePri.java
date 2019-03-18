package org.pace.service.primary;

import java.util.List;

import org.pace.model.Category;

public interface CategoryServicePri {
	
	 Category findBycategoryId(int id);
	 
	 void save(Category data);
	 
	 void update(Category data);
	 
	 List<Category> findAll();
}