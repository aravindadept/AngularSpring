package org.pace.service.secondaryimpl;

import org.pace.model.Max;
import org.pace.repositories.secondary.MaxRepoSec;
import org.pace.service.secondary.MaxServiceSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("MaxServiceSec")
@Transactional("transactionalManagerSec")
public class MaxServiceImplSec implements MaxServiceSec {
	
	@Autowired
	private MaxRepoSec maxRepoSec;
	 	 
    public Max findByid(int id) {
        return maxRepoSec.findByid(id);
    }

	public void save(Max data) {
		maxRepoSec.save(data);		
	}	
	
	public void update(Max data) {
		save(data);
	}
}
