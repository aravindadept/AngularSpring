package org.pace.service.secondaryimpl;

import java.util.List;

import org.pace.model.PoItem;
import org.pace.repositories.secondary.PoItemRepoSec;
import org.pace.service.secondary.PoItemServiceSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("poItemServiceSec")
@Transactional("transactionalManagerSec")
public class PoItemServiceImplSec implements PoItemServiceSec {
	
	@Autowired
	private PoItemRepoSec poItemRepoSec;
	 	 
    public PoItem findByid(int id) {
        return poItemRepoSec.findByid(id);
    }
           
    public List<PoItem> findAll(){    	
    	return poItemRepoSec.findAll();    	
    }

	public void save(PoItem data) {
		poItemRepoSec.save(data);		
	}	
	
	public void update(PoItem data) {
		save(data);
	}
}
