package org.pace.service.primaryimpl;

import java.util.List;

import org.pace.model.PoItem;
import org.pace.repositories.primary.PoItemRepoPri;
import org.pace.service.primary.PoItemServicePri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("poItemServicePri")
@Transactional("transactionalManagerPri")
public class PoItemServiceImplPri implements PoItemServicePri {
	
	@Autowired
	private PoItemRepoPri poItemRepoPri;
	 	 
    public PoItem findByid(int id) {
        return poItemRepoPri.findByid(id);
    }
           
    public List<PoItem> findAll(){    	
    	return poItemRepoPri.findAll();    	
    }

	public void save(PoItem data) {
		poItemRepoPri.save(data);		
	}	
	
	public void update(PoItem data) {
		save(data);
	}
}
