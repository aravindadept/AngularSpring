package org.pace.repositories.secondary;

import org.pace.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepoSec extends JpaRepository <Item,Long>{
	
	Item findByitemId(int id);	

}
