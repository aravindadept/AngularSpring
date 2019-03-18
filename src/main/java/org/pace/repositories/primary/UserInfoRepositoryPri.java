package org.pace.repositories.primary;

import org.pace.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepositoryPri extends JpaRepository<UserInfo, Long> {
	
	UserInfo findByuserName(String username);

}
