package org.pace.service.secondary;

import java.util.List;

import org.pace.model.PoItem;

public interface PoItemServiceSec {
	
	PoItem findByid(int id);
	
	List<PoItem> findBypoId(int id);
	 
	 void save(PoItem data);
	 
	 void update(PoItem data);
	 
	 List<PoItem> findAll();
}