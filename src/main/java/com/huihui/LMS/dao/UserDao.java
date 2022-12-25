package com.huihui.LMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.huihui.LMS.pojo.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	User findByUsername(@Param("username")String username);
	
	User findByEmail(@Param("email")String email);
}
