package org.pace.service.primaryimpl;

import java.util.List;

import org.pace.model.Item;
import org.pace.repositories.primary.ItemRepoPri;
import org.pace.service.primary.ItemServicePri;
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
           
    public List<Item> findAll(){    	
    	return itemRepoPri.findAll();    	
    }

	public void save(Item data) {
		itemRepoPri.save(data);		
	}	
	
	public void update(Item data) {
		save(data);
	}
}
