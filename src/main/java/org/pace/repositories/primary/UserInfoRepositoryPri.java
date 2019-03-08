package org.pace.repositories.primary;

import org.pace.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepositoryPri extends JpaRepository<UserInfo, Long> {
	
	UserInfo findByuserName(String username);

}
