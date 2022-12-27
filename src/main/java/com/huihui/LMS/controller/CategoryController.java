package com.huihui.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huihui.LMS.pojo.Category;
import com.huihui.LMS.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/addcat")
	public String addcat(Category category) {
		try {
			categoryService.addcat(category);
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			return "redirect:/addcategory";
		}
		return "redirect:/category/getcat";
	}
	
	@GetMapping("/getcat")
	public String getcat(Model model) {
		categoryService.getcat(model);
		return "category";
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public String deleteCat(@PathVariable("id")Integer id) {
		try {
			categoryService.deleteByID(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return "false";
		}
		return "true";
	}
}
