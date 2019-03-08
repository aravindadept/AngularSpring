package org.pace.service.primary;

import java.util.List;

import org.pace.model.Item;
import org.pace.repositories.primary.ItemRepoPri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("itemServicePri")
@Transactional("transactionalManagerPri")
public class ItemServiceImplPri implements ItemServicePri {
	
	@Autowired
	private ItemRepoPri itemRepoPri;
	 	 
    public Item findByitemId(int id) {
        return itemRepoPri.findByitemId(id);
    }
           
    public List<Item> findAllItem(){    	
    	return itemRepoPri.findAll();    	
    }

	public void saveItem(Item item) {
		itemRepoPri.save(item);		
	}	
	
	public void updateItem(Item item) {
		saveItem(item);
	}
}
