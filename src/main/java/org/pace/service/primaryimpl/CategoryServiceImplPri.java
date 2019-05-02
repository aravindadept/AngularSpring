package org.pace.service.primaryimpl;

import java.util.List;

import org.pace.model.Category;
import org.pace.repositories.primary.CategoryRepoPri;
import org.pace.service.primary.CategoryServicePri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    public List<Category> findAll(){
    	return categoryRepoPri.findAll();
    }
    
    public Page<Category> findByPageSize(Pageable pageable){
    	return categoryRepoPri.findAll(pageable);
    }

	public void save(Category data) {
		categoryRepoPri.save(data);
	}	
	
	public void update(Category data) {
		save(data);
	}	

}
