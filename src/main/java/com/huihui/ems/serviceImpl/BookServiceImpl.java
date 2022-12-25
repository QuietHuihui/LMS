package com.huihui.ems.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huihui.ems.dao.BookDao;
import com.huihui.ems.pojo.Book;
import com.huihui.ems.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Override
	public void addBook(Book book) {
		bookDao.save(book);
		
	}

}
