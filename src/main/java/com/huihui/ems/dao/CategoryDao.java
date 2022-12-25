package com.huihui.ems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.huihui.ems.pojo.Category;
import com.huihui.ems.wrapper.CategoryWrapper;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	Category findByName( @Param("name") String name);

	List<CategoryWrapper> getAllCat();

}
