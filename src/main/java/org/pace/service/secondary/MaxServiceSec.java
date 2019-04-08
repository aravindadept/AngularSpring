package org.pace.service.secondary;

import org.pace.model.Max;

public interface MaxServiceSec {
	
	 Max findByid(int id);
	 
	 void save(Max data);

	 void update(Max data);
	 
}