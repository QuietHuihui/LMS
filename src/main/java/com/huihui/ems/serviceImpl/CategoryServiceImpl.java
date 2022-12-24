package com.huihui.ems.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.huihui.ems.dao.CategoryDao;
import com.huihui.ems.pojo.Category;
import com.huihui.ems.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void addcat(Category category) {
		
		//根据种类名查询数据库中是否以及存在此种类
		Category catInDB = categoryDao.findByName(category.getName());
		
		if(!ObjectUtils.isEmpty(catInDB))throw new RuntimeException("当前类别已存在。");
		
		System.out.println("这是类别的名称 "+category.getName());
		categoryDao.save(category);
		
	}

}
