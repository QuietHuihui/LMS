package com.huihui.LMS.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.huihui.LMS.dao.BookDao;
import com.huihui.LMS.pojo.Book;
import com.huihui.LMS.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao bookDao;


	@Override
	public void save(Book book) {
		bookDao.save(book);
		
	}

	@Override
	public void getBook(Model model) {
		 List<Book> books = bookDao.findAll();
		 model.addAttribute("books",books);
	}

	@Override
	public void addBook(Book book, HttpSession session, MultipartFile photo, String tname) {
		// TODO Auto-generated method stub
		
	}

	


}