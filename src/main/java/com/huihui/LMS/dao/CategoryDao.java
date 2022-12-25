package com.huihui.LMS.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.huihui.LMS.pojo.Category;
import com.huihui.LMS.wrapper.CategoryWrapper;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	Category findByName( @Param("name") String name);

	List<CategoryWrapper> getAllCat();

}
