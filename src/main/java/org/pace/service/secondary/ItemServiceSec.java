package org.pace.service.secondary;

import java.util.List;

import org.pace.model.Item;

public interface ItemServiceSec {
	
	 Item findByitemId(int id);
	 
	 void save(Item data);
	 
	 void update(Item data);
	 
	 List<Item> findAll();
}