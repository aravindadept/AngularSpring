package org.pace.repositories.secondary;

import org.pace.model.PoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoItemRepoSec extends JpaRepository <PoItem,Long>{
	
	PoItem findByid(int id);	

}
