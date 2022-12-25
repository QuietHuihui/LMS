package com.huihui.ems.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huihui.ems.pojo.Book;
import com.huihui.ems.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	
	@RequestMapping("/addbook")
	public String addBook(Book book,HttpSession session) {
		try {
			bookService.addBook(book,session);
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			return "addbook";
		}
		return "addbook";
	}
}
