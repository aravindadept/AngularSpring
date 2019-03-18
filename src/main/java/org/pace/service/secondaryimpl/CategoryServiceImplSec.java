package org.pace.service.secondaryimpl;

import java.util.List;

import org.pace.model.Category;
import org.pace.repositories.secondary.CategoryRepoSec;
import org.pace.service.secondary.CategoryServiceSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryServiceSec")
@Transactional("transactionalManagerSec")
public class CategoryServiceImplSec implements CategoryServiceSec {
	
	 	@Autowired
	 	private CategoryRepoSec categoryRepoSec;
	 
    public Category findBycategoryId(int id) {
        return categoryRepoSec.findBycategoryId(id);
    }
       
    public List<Category> findAll(){
    	return categoryRepoSec.findAll();
    }	
    
    public void save(Category data) {
    	categoryRepoSec.save(data);
	}
    
	public void update(Category data) {		
		save(data);
	}	
}
