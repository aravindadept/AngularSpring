package org.pace.service.primaryimpl;

import org.pace.model.Max;
import org.pace.repositories.primary.MaxRepoPri;
import org.pace.service.primary.MaxServicePri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("MaxServicePri")
@Transactional("transactionalManagerPri")
public class MaxServiceImplPri implements MaxServicePri {
	
	@Autowired
	private MaxRepoPri maxRepoPri;
	 	 
    public Max findByid(int id) {
        return maxRepoPri.findByid(id);
    }

	public void save(Max data) {
		maxRepoPri.save(data);		
	}	
	
	public void update(Max data) {
		save(data);
	}
}
