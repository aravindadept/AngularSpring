package org.pace.repositories.primary;

import org.pace.model.Max;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxRepoPri extends JpaRepository <Max,Long>{
	
	Max findByid(int id);	

}
