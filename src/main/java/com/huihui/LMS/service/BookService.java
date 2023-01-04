package com.huihui.LMS.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.huihui.LMS.pojo.Book;

public interface BookService {

	void addBook(Book book,HttpSession session,MultipartFile photo,String tname);

	void save(Book book);

	void getBook(HttpServletRequest request, Model model);

	void deleteByID(Integer id);

	Book findById(Integer id);

	void updateBook(Book book);

	Book getBookById(Integer id);

	List<Book> findBySearch(String search, HttpServletRequest request, Model model);

}
