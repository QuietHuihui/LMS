package com.huihui.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huihui.LMS.pojo.Book;
import com.huihui.LMS.service.BookService;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/addborrow/{id}")
	public String addBorrow(@PathVariable Integer id,Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book",book);
		return "addborrow";
	}

}
