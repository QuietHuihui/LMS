package com.huihui.LMS.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.huihui.LMS.dao.BookDao;
import com.huihui.LMS.pojo.Book;
import com.huihui.LMS.pojo.Category;
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

	@Override
	public void deleteByID(Integer id) {
		Path root = Paths.get("photo");
		@SuppressWarnings("deprecation")
		Book bk = bookDao.getById(id);
		try {
			if(!(bk.getCover()==null)) {
				Files.delete(root.resolve(bk.getCover()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		bookDao.deleteById(id);
		
	}

	@Override
	public Book findById(Integer id) {
		try {
			Optional<Book> optional = bookDao.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateBook(Book book) {
		System.out.println("inserviceimpl"+book.getTitle());
		System.out.println("thisistheid"+book.getId());
		bookDao.updateBook(book.getId(),book.getIsbn(),book.getTitle(),book.getPress(),
				book.getCover(),book.getAuthor(),book.getAmount(),book.getCategory());
		
	}

	@Override
	public Book getBookById(Integer id) {
		try {
			@SuppressWarnings("deprecation")
			Book book = bookDao.getById(id);
			return book;
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	


}
