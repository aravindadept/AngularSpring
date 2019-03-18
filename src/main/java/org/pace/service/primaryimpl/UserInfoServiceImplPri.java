package org.pace.service.primaryimpl;


import java.util.List;

import org.pace.model.UserInfo;
import org.pace.repositories.primary.UserInfoRepositoryPri;
import org.pace.service.primary.UserInfoServicePri;
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

    public List<UserInfo> findAll(){    	
    	return userInfoRepositoryPri.findAll();    	
    }
}
