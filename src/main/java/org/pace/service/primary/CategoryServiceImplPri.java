package org.pace.service.primary;

import java.util.List;

import org.pace.model.Category;
import org.pace.repositories.primary.CategoryRepoPri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryServicePri")
@Transactional("transactionalManagerPri")
public class CategoryServiceImplPri implements CategoryServicePri {
	
	 	@Autowired
	 	private CategoryRepoPri categoryRepoPri;
	 
	 
    public Category findBycategoryId(int id) {
        return categoryRepoPri.findBycategoryId(id);
    }
    
    public List<Category> findAllCategory(){
    	return categoryRepoPri.findAll();
    }

	public void saveCategory(Category category) {
		categoryRepoPri.save(category);
	}	
	
	public void updateCategory(Category category) {
		saveCategory(category);
	}	

}
