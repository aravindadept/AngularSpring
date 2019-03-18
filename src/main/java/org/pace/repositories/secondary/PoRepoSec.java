package org.pace.repositories.secondary;

import org.pace.model.Po;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoRepoSec extends JpaRepository <Po,Long>{
	
	Po findByid(int id);	

}
