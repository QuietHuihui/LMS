package com.huihui.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huihui.ems.pojo.User;
import com.huihui.ems.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/register")
	public String register(User user) {
		log.debug("用户名:{},邮箱:{},密码:{}",user.getName(),user.getEmail(),user.getPassword());
		
		try {
			userService.register(user);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			//注册失败回到注册
			return "redirect:/register";
		}
		//注册成功
		return "redirect:/login";
	}
}
