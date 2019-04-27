package org.pace.service.primary;

import java.util.List;

import org.pace.model.PoItem;

public interface PoItemServicePri {
	
	PoItem findByid(int id);
	
	List<PoItem> findBypoId(int poId);
	 
	 void save(PoItem data);
	 
	 void update(PoItem data);
	 
	 List<PoItem> findAll();
}