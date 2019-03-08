package org.pace.service.primary;


import java.util.List;

import org.pace.model.Item;
import org.pace.model.UserInfo;
import org.pace.repositories.primary.UserInfoRepositoryPri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userInfoServicePri")
@Transactional("transactionalManagerPri")
public class UserInfoServiceImplPri implements UserInfoServicePri {
	
	@Autowired
	private UserInfoRepositoryPri userInfoRepositoryPri;
	 	 
    public UserInfo findByuserName(String username) {
        return userInfoRepositoryPri.findByuserName(username);
    }

    public List<UserInfo> findAllUserInfo(){    	
    	return userInfoRepositoryPri.findAll();    	
    }
}
