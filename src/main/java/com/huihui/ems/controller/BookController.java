package com.huihui.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huihui.ems.pojo.Book;
import com.huihui.ems.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	
	@PostMapping("/addbook")
	public String addBook(Book book) {
		try {
			bookService.addBook(book);
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			return "redirect:/addbook";
		}
		return "redirect:/book";
	}
}
