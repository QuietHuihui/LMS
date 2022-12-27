package com.huihui.LMS.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.huihui.LMS.pojo.Book;
import com.huihui.LMS.pojo.Category;

public interface BookDao extends JpaRepository<Book, Integer>{

	@Transactional
	@Modifying
	@Query("update Book b set b.isbn=?2,b.title=?3,b.press=?4,b.cover=?5,b.author=?6,b.amount=?7,b.category=?8 where b.id=?1 ")
	void updateBook(Integer id, String isbn, String title, String press, String cover, String author, Integer amount,
			Category category);



}
