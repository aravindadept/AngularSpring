package org.pace.service.secondary;


import java.util.List;

import org.pace.model.UserInfo;

public interface UserInfoServiceSec {
	
	UserInfo findByuserName(String username);
	
	 List<UserInfo> findAll();
	 
/*	 void saveUserInfo(UserInfo userinfo);
	 
	 void updateUserInfo(UserInfo userinfo);
	 
	 List<UserInfo> findAllUserInfo();*/
}