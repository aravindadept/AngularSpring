package org.pace.service.secondaryimpl;


import java.util.List;

import org.pace.model.UserInfo;
import org.pace.repositories.secondary.UserInfoRepositorySec;
import org.pace.service.secondary.UserInfoServiceSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userInfoServiceSec")
@Transactional("transactionalManagerSec")
public class UserInfoServiceImplSec implements UserInfoServiceSec {
	
	@Autowired
	private UserInfoRepositorySec userInfoRepositorySec;
	 	 
    public UserInfo findByuserName(String username) {
        return userInfoRepositorySec.findByuserName(username);
    }

    public List<UserInfo> findAll(){    	
    	return userInfoRepositorySec.findAll();    	
    }
}
