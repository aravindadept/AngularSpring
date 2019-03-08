package org.pace.service.secondary;

import java.util.List;

import org.pace.model.Category;
import org.pace.repositories.secondary.CategoryRepoSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryServiceSec")
@Transactional("transactionalManagerPri")
public class CategoryServiceImplSec implements CategoryServiceSec {
	
	 	@Autowired
	 	private CategoryRepoSec categoryRepoSec;
	 
    public Category findBycategoryId(int id) {
        return categoryRepoSec.findBycategoryId(id);
    }
       
    public List<Category> findAllCategory(){
    	return categoryRepoSec.findAll();
    }	
    
    public void saveCategory(Category category) {
    	categoryRepoSec.save(category);
	}
    
	public void updateCategory(Category category) {		
		saveCategory(category);
	}	
}
