package org.pace.service.secondary;

import java.util.List;
import org.pace.model.Item;
import org.pace.repositories.secondary.ItemRepoSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("itemServiceSec")
@Transactional("transactionalManagerPri")
public class ItemServiceImplSec implements ItemServiceSec {
	
	@Autowired
	private ItemRepoSec itemRepoSec;
	 	 
    public Item findByitemId(int id) {
        return itemRepoSec.findByitemId(id);
    }
           
    public List<Item> findAllItem(){    	
    	return itemRepoSec.findAll();    	
    }

	public void saveItem(Item item) {
		itemRepoSec.save(item);		
	}
	
	public void updateItem(Item item) {
		saveItem(item);
	}	
}
