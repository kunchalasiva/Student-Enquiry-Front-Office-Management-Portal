package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

	@Query("SELECT COUNT(userEmail) FROM UserEntity WHERE userEmail=:mail")
	public Integer findByEmailId(String mail);
	
	public UserEntity findByUserEmail(String mail);
    
	public UserEntity findByUserNameAndUserPwd(String userName, String userPwd);
}
