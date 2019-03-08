package org.pace.repositories.secondary;

import org.pace.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepoSec extends JpaRepository <Category,Long>{
	
	Category findBycategoryId(int id);

}
