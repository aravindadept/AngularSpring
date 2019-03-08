package org.pace.service.secondary;

import java.util.List;

import org.pace.model.Item;

public interface ItemServiceSec {
	
	 Item findByitemId(int id);
	 
	 void saveItem(Item item);
	 
	 void updateItem(Item item);
	 
	 List<Item> findAllItem();
}