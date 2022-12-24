package com.huihui.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.huihui.ems.pojo.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	Category findByName( @Param("name") String name);

}
