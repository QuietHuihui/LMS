package com.huihui.LMS.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huihui.LMS.pojo.User;
import com.huihui.LMS.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String register(User user) {
		log.debug("用户名:{},邮箱:{},密码:{}",user.getName(),user.getEmail(),user.getPassword());
		
		try {
			//默认设置为普通用户
			user.setUsertype("normal");
			userService.register(user);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			//注册失败回到注册
			return "redirect:/register";
		}
		//注册成功
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public String login(String username,String password,HttpSession session){
		log.debug("本次登陆的用户名: {}",username);
		log.debug("本次登陆的密码: {}",password);
		userService.login(username,password);
		try {
			User user = userService.login(username,password);
			//保存用户信息
			session.setAttribute("user", user);
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			return "redirect:/login";
		}
		return "redirect:/book/getbook";
		
	}
}
