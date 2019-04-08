package org.pace.repositories.secondary;

import org.pace.model.Max;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxRepoSec extends JpaRepository <Max,Long>{
	
	Max findByid(int id);	

}
