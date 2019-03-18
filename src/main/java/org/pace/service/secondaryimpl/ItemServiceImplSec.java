package org.pace.service.secondaryimpl;

import java.util.List;
import org.pace.model.Item;
import org.pace.repositories.secondary.ItemRepoSec;
import org.pace.service.secondary.ItemServiceSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("itemServiceSec")
@Transactional("transactionalManagerSec")
public class ItemServiceImplSec implements ItemServiceSec {
	
	@Autowired
	private ItemRepoSec itemRepoSec;
	 	 
    public Item findByitemId(int id) {
        return itemRepoSec.findByitemId(id);
    }
           
    public List<Item> findAll(){    	
    	return itemRepoSec.findAll();    	
    }

	public void save(Item data) {
		itemRepoSec.save(data);		
	}
	
	public void update(Item data) {
		save(data);
	}	
}
