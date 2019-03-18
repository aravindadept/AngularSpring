package org.pace.service.primaryimpl;

import java.util.List;

import org.pace.model.Po;
import org.pace.repositories.primary.PoRepoPri;
import org.pace.service.primary.PoServicePri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("poServicePri")
@Transactional("transactionalManagerPri")
public class PoServiceImplPri implements PoServicePri {
	
	@Autowired
	private PoRepoPri poRepoPri;
	 	 
    public Po findByid(int id) {
        return poRepoPri.findByid(id);
    }
           
    public List<Po> findAll(){    	
    	return poRepoPri.findAll();    	
    }

	public void save(Po data) {
		poRepoPri.save(data);		
	}	
	
	public void update(Po data) {
		save(data);
	}
}
