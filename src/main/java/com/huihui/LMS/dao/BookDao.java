package com.huihui.LMS.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.huihui.LMS.pojo.Book;
import com.huihui.LMS.pojo.Category;

public interface BookDao extends JpaRepository<Book, Integer>{

	@Transactional
	@Modifying
	@Query("update Book b set b.isbn=?2,b.title=?3,b.press=?4,b.cover=?5,b.author=?6,b.amount=?7,b.category=?8 where b.id=?1 ")
	void updateBook(Integer id, String isbn, String title, String press, String cover, String author, Integer amount,
			Category category);

	//根据书名、作者或者ISBN或者出版社查询图书
	@Query(value="select * from Book where title like '%'||?1||'%' or author like '%'||?1||'%' or isbn like '%'||?1||'%' or press like '%'||?1||'%'",nativeQuery = true)
	List<Book> findBySearch(@Param("search")String search);
	
	//分页展示，start是起始索引，step是步长
	@Query(value="select* from Book limit :start,:step",nativeQuery = true)
	List<Book> findByPage(@Param("start")Integer start,@Param("step") Integer step);



}
