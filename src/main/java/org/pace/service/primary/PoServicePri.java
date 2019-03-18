package org.pace.service.primary;

import java.util.List;

import org.pace.model.Po;

public interface PoServicePri {
	
	Po findByid(int id);
	 
	 void save(Po data);
	 
	 void update(Po data);
	 
	 List<Po> findAll();
}