package org.pace.service.primary;


import java.util.List;

import org.pace.model.UserInfo;

public interface UserInfoServicePri {
	
	UserInfo findByuserName(String username);
	
	 List<UserInfo> findAllUserInfo();
	 
/*	 void saveUserInfo(UserInfo userinfo);
	 
	 void updateUserInfo(UserInfo userinfo);
	 
	 List<UserInfo> findAllUserInfo();*/
}