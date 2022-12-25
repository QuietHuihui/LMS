package com.huihui.ems.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.huihui.ems.dao.BookDao;
import com.huihui.ems.dao.CategoryDao;
import com.huihui.ems.pojo.Book;
import com.huihui.ems.service.BookService;
import com.huihui.ems.wrapper.CategoryWrapper;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void addBook(Book book,HttpSession session) {
		
		List<CategoryWrapper>categories = categoryDao.getAllCat();
		session.setAttribute("categories",categories);
		bookDao.save(book);
	}

}
