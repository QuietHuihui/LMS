package com.huihui.ems.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.huihui.ems.dao.UserDao;
import com.huihui.ems.pojo.User;
import com.huihui.ems.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public void register(User user) {
		//首先根据用户名查询数据库中是否存在该用户
		User userInDb_1 = userDao.findByUsername(user.getUsername());
		User userInDb_2 = userDao.findByEmail(user.getName());
		//判断用户是否存在
		if(!ObjectUtils.isEmpty(userInDb_1)) throw new RuntimeException("当前用户名已被注册！");
		//判断邮箱是否存在
		else if(!ObjectUtils.isEmpty(userInDb_2))throw new RuntimeException("当前邮箱已被注册！");
		//注册用户
		userDao.save(user);
		
	}

}
