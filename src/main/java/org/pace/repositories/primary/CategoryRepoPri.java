package org.pace.repositories.primary;

import org.pace.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepoPri extends JpaRepository <Category,Long>{
	
	Category findBycategoryId(int id);	

}
