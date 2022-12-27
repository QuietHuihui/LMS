package com.huihui.LMS.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.huihui.LMS.pojo.Category;
import com.huihui.LMS.wrapper.CategoryWrapper;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	Category findByName( @Param("name") String name);

	List<CategoryWrapper> getAllCat();

	@Transactional
	@Modifying
	@Query("update Category c set c.name = ?1 where c.id = ?2")
	void updateCat(String name,Integer id);

}
