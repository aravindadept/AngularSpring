package org.pace.security;

import java.util.Arrays;

import org.pace.controller.BaseController;
import org.pace.model.UserInfo;
import org.pace.service.primary.UserInfoServicePri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCheckService implements UserDetailsService {
	
	public static final Logger logger = LoggerFactory.getLogger(UserCheckService.class);
	
	
	@Autowired
	private UserInfoServicePri userInfoServicePri;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		UserInfo activeUserInfo =null;
		String dBuserName=null;
		
		if(userInfoServicePri.findByuserName(username)!=null) {
		 activeUserInfo = userInfoServicePri.findByuserName(username);
		  dBuserName = activeUserInfo.getUserName();
		}
		
		if(dBuserName == null){ throw new UsernameNotFoundException("User not authorized.");}
		
		    GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole().getRoleName());
		    
		    UserDetails userDetails = (UserDetails)new User(dBuserName,activeUserInfo.getPassword(), Arrays.asList(authority));
		    
		    return userDetails;
	}
	
	
}

