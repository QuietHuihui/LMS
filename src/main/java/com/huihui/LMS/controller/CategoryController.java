package com.huihui.LMS.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huihui.LMS.dao.CategoryDao;
import com.huihui.LMS.pojo.Category;
import com.huihui.LMS.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryDao categoryDao;
	
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
	
	//打开更新页面，并不进行更新
	@RequestMapping("/edit/{id}")
	public String editCat(@PathVariable("id")Integer id,Model model) {
		Category cat = categoryService.findById(id);
		model.addAttribute("cat",cat);
		return "updatecat";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String updateCat(String name,Integer id) {
		categoryService.updateCat(name,id);
		return "true";
	}
}
