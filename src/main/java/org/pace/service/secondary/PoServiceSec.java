package org.pace.service.secondary;

import java.util.List;

import org.pace.model.Po;

public interface PoServiceSec {
	
	Po findByid(int id);
	 
	 void save(Po data);
	 
	 void update(Po data);
	 
	 List<Po> findAll();
}