package com.huihui.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

//@RestController RestController只能够返回一个字符串(便于处理JSON)
//而@Controller 返回的字符串，对应的是templates文件夹下与此字符串相匹配的页面
@Controller
@RequestMapping("demo")
public class DemoController {
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping("demo")
	public String demo(Model model) {
		log.debug("demo ok");
		model.addAttribute("msg","hello thymeleaf");
		return "demo";
	}
}
