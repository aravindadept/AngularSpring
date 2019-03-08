package org.pace.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalVariables {

	
		
		public static Boolean cloudFlag;
		
		@Value("${connection.cloudFlag}")
		public void setCloudFlag(Boolean cF) {
			
			cloudFlag=cF;
			
		}

}