package com.huihui.LMS.serviceImpl;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import com.huihui.LMS.dao.UserDao;
import com.huihui.LMS.pojo.User;
import com.huihui.LMS.service.UserService;

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
		//给明文的密码加密，相同字符串多次使用md5加密，加密的结果始终是相同的
		String codedPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
		user.setPassword(codedPassword);
		//注册用户
		userDao.save(user);
		
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.findByUsername(username);
		//判断用户名是否存在
		if(ObjectUtils.isEmpty(user))throw new RuntimeException("用户名不存在！");
		//将用户输入的密码加密，然后再将其与数据库中那个加密过的密码进行比较
		String passwordSecret = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
		//比较密码
		if(!user.getPassword().equals(passwordSecret))throw new RuntimeException("密码错误！");
		
		return user;
	}

	@Override
	public User getUserById(Integer uid) {
		try {
			User user = userDao.getById(uid);
			return user;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
