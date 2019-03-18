package org.pace.service.primary;

import java.util.List;

import org.pace.model.Item;
import org.pace.model.PoItem;

public interface ItemServicePri {
	
	 Item findByitemId(int id);
	 
	 void save(Item data);
	 
	 void update(Item data);
	 
	 List<Item> findAll();
}