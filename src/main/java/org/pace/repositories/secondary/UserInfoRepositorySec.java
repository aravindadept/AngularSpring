package org.pace.repositories.secondary;

import org.pace.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepositorySec extends JpaRepository<UserInfo, Long> {
	
	UserInfo findByuserName(String username);

}
