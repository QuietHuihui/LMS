package com.huihui.LMS.service;

import org.springframework.ui.Model;

import com.huihui.LMS.pojo.Category;

public interface CategoryService {

	void addcat(Category category);

	void getcat(Model model);

}
