package com.huihui.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huihui.ems.pojo.Category;
import com.huihui.ems.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/addcat")
	public String addcat(Category category) {
		try {
			categoryService.addcat(category);
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			return "redirect:/addcategory";
		}
		return "redirect:/book";
	}
}
