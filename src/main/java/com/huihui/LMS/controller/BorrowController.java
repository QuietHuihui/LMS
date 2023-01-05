package com.huihui.LMS.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String addBorrow(String bookid,String userid,String borrowedFrom,String borrowedTo,HttpSession session) {
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
			return "redirect:/book/getbook";
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/book/getbook";		
	}
	
	//获取一个用户的借书记录
	@GetMapping("/getborrow/{id}")
	public String getBorrow(@PathVariable Integer id,Model model) {
		List<Borrow>borrows = borrowService.getBorrow(id);
		model.addAttribute("borrows", borrows);
		return "getborrow";
	}
	
	//获取所有用户的借书记录
	@GetMapping("/getallborrow")
	public String getAllBorrow(Model model) {
		List<Borrow>borrows = borrowService.getAllBorrow();
		model.addAttribute("borrows",borrows);
		return "getallborrow";
	}
	
	//还书
	@GetMapping("/returnbook/{id}")
	@ResponseBody
	//id是借阅记录的id
	public String returnBook(@PathVariable Integer id) {
		try {
			//获取此条借阅记录
			Borrow borrow = borrowService.getBorrowById(id);
			
			//获取还书时间
		    Calendar calendar= Calendar.getInstance();
		    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		    java.sql.Date returnDate = java.sql.Date.valueOf(dateFormat.format(calendar.getTime()));
		    
		    //设置此条记录的还书时间
		    borrow.setReturnDate(returnDate);
		    borrowService.save(borrow);
		    
		    //设置还了的书的书本数量加一
		    Book book = bookService.getBookById(borrow.getBook().getId());
		    book.setAmount(book.getAmount()+1);
		    bookService.save(book);
		    
		    return "true";
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	    return "false";
	}
}
