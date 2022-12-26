package com.huihui.LMS.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	//通过此配置，不需要为每一个访问thymeleaf模板页面单独开发一个controller请求
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//viewcontroller 请求路径 viewname 跳转视图
		registry.addViewController("login").setViewName("login");
		
		registry.addViewController("register").setViewName("register");
		
		//registry.addViewController("book").setViewName("book");
		
		registry.addViewController("addcategory").setViewName("addcategory");
		
		registry.addViewController("getcat").setViewName("category");
		
		registry.addViewController("addbook").setViewName("addbook");
	}
}
