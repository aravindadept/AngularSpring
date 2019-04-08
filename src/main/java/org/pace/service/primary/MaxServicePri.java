package org.pace.service.primary;

import org.pace.model.Max;

public interface MaxServicePri {
	
	 Max findByid(int id);
	 
	 void save(Max data);

	 void update(Max data);
	 
}