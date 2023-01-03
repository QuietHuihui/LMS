package com.huihui.LMS.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import com.huihui.LMS.dao.BorrowDao;
import com.huihui.LMS.pojo.Book;
import com.huihui.LMS.pojo.Borrow;
import com.huihui.LMS.pojo.User;
import com.huihui.LMS.service.BookService;
import com.huihui.LMS.service.BorrowService;
import com.huihui.LMS.service.UserService;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BorrowService borrowService;
	
	//创建借阅记录
	@RequestMapping("/createborrow/{id}")
	public String createBorrow(@PathVariable Integer id,Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book",book);
		return "createborrow";
	}
	
	//把借阅记录添加到数据库中
	@PostMapping("/addborrow")
	@ResponseBody
	public String addBorrow(String bookid,String userid,String borrowedFrom,String borrowedTo) {
		try {
			System.out.println("Borrow from and to");
			System.out.println(borrowedFrom);
			System.out.println(borrowedTo);
			Integer bid = Integer.parseInt(bookid);
			Integer uid = Integer.parseInt(userid);
			
			//根据字符串先转换为util.Date
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date bf = format.parse(borrowedFrom);
			Date bt = format.parse(borrowedTo);
			
			//把util.Date转换成sql.Date
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date bf_sql = java.sql.Date.valueOf(dateFormat.format(bf));
			java.sql.Date bt_sql = java.sql.Date.valueOf(dateFormat.format(bt));
			
			//把新建的borrow记录存到数据库中
			Borrow borrow = new Borrow();
			Book book = bookService.getBookById(bid);
			User user = userService.getUserById(uid);
			//借阅时减去一本书
			book.setAmount(book.getAmount()-1);
			//设置各项属性
			borrow.setBorrowedFrom(bf_sql);
			borrow.setBorrowedTo(bt_sql);
			borrow.setUser(user);
			borrow.setBook(book);
			//保存借阅记录
			borrowService.save(borrow);
			return "true";
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "false";			
	}
	
	@GetMapping("/getAllBorrow")
	@ResponseBody
	public String getAllBorrow() {
		return (borrowService.getAllBorrow()).toString();
	}
	
	//获取一个用户的借书记录
	@GetMapping("/getborrow/{id}")
	public String getBorrow(@PathVariable Integer id,Model model) {
		List<Borrow>borrows = borrowService.getBorrow(id);
		model.addAttribute("borrows", borrows);
		System.out.println(id);
		System.out.println(borrows);
		return "getborrow";
	}
}
