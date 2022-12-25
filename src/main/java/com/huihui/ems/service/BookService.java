package com.huihui.ems.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.huihui.ems.pojo.Book;

public interface BookService {

	void addBook(Book book,HttpSession session);

}
