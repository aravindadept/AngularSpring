package org.pace.repositories.primary;

import org.pace.model.Po;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoRepoPri extends JpaRepository <Po,Long>{
	
	Po findByid(int id);	

}
