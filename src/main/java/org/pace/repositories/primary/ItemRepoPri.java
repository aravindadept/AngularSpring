package org.pace.repositories.primary;

import org.pace.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepoPri extends JpaRepository <Item,Long>{
	
	Item findByitemId(int id);	

}
