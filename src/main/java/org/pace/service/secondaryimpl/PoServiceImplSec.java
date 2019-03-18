package org.pace.service.secondaryimpl;

import java.util.List;

import org.pace.model.Po;
import org.pace.repositories.secondary.PoRepoSec;
import org.pace.service.secondary.PoServiceSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("poServiceSec")
@Transactional("transactionalManagerSec")
public class PoServiceImplSec implements PoServiceSec {
	
	@Autowired
	private PoRepoSec poRepoSec;
	 	 
    public Po findByid(int id) {
        return poRepoSec.findByid(id);
    }
           
    public List<Po> findAll(){    	
    	return poRepoSec.findAll();    	
    }

	public void save(Po data) {
		poRepoSec.save(data);		
	}	
	
	public void update(Po data) {
		save(data);
	}
}
