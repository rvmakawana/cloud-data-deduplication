package com.example.demo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.user;

public interface userrepository extends CrudRepository<user,Integer> {
 
	public user findByUsernameAndPassword(String username, String password);	
	
	public user findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("UPDATE user u set u.alpha = ?1 where u.username = ?2")
	 public void setUserInfoById(String re,String r1);
}
