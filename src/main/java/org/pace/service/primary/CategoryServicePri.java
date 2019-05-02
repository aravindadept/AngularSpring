package org.pace.service.primary;

import java.util.List;

import org.pace.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryServicePri {
	
	 Category findBycategoryId(int id);
	 
	 void save(Category data);
	 
	 void update(Category data);
	 
	 List<Category> findAll();
	 
	 Page<Category> findByPageSize(Pageable pageable);
}