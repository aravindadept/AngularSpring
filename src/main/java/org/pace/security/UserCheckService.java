package org.pace.security;

import java.util.Arrays;

import org.pace.model.UserInfo;
import org.pace.service.primary.UserInfoServicePri;
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
	
	@Autowired
	private UserInfoServicePri userInfoServicePri;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		UserInfo activeUserInfo = userInfoServicePri.findByuserName(username);
		
		    String dBuserName = activeUserInfo.getUserName();
		    
		    if(dBuserName == null){
		        throw new UsernameNotFoundException("User not authorized.");
		    }
		    GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole().getRoleName());
		    
		    UserDetails userDetails = (UserDetails)new User(dBuserName,activeUserInfo.getPassword(), Arrays.asList(authority));
		    return userDetails;
	}
	
	
/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("SECURITY LOG====>"+username);
		User user = userRepositoryPri.findByuserName(username);
		
		if(user==null) throw new UsernameNotFoundException("User 404");
			
		System.out.println("SECURITY LOG====>"+new UserPrincipal(user));
		return new UserPrincipal(user);
	}*/

}

