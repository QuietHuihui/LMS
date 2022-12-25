package com.huihui.ems.service;

import org.springframework.ui.Model;

import com.huihui.ems.pojo.Category;

public interface CategoryService {

	void addcat(Category category);

	void getcat(Model model);

}
