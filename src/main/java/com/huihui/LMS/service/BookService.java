package com.huihui.LMS.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.huihui.LMS.pojo.Book;

public interface BookService {

	void addBook(Book book,HttpSession session,MultipartFile photo,String tname);

	void save(Book book);

	void getBook(Model model);

}
