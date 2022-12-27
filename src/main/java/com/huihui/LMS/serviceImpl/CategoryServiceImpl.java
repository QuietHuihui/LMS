package com.huihui.LMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import com.huihui.LMS.dao.CategoryDao;
import com.huihui.LMS.pojo.Category;
import com.huihui.LMS.service.CategoryService;
import com.huihui.LMS.wrapper.CategoryWrapper;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void addcat(Category category) {
		
		//根据种类名查询数据库中是否以及存在此种类
		Category catInDB = categoryDao.findByName(category.getName());
		
		if(!ObjectUtils.isEmpty(catInDB))throw new RuntimeException("当前类别已存在。");
		
		categoryDao.save(category);
		
	}

	@Override
	public void getcat(Model model) {
		List<CategoryWrapper>categories = categoryDao.getAllCat();
		model.addAttribute("categories",categories);
	}

	@Override
	public void deleteByID(Integer id) {
		try {
			categoryDao.deleteById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
