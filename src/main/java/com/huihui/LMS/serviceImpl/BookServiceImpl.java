package com.huihui.LMS.serviceImpl;

import java.awt.print.Pageable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
	public void getBook(HttpServletRequest request, Model model) {
		Integer index = 1;
		Integer step = 3;
		String strIndex = request.getParameter("index");
		if(StringUtils.hasText(strIndex)) {
			index = Integer.valueOf(strIndex);
			if(index<1)index=1;
		}
		String strStep = request.getParameter("step");
		if(StringUtils.hasText(strStep)) {
			step = Integer.valueOf(strStep);
			if(step<3)step=3;
		}
		System.out.println("test");
		System.out.println(index);
		System.out.println(step);
		//Sort sort = Sort.by(Sort.DEFAULT_DIRECTION,"id");
		PageRequest pageRequest = PageRequest.of(index-1,step,Sort.by("id"));
		
		Page<Book>books = bookDao.findAll(pageRequest);
		List<Book>temp = bookDao.findAll();
		Integer allCount = temp.size();
		Integer start = step*(index-1);
		List<Book>bucher = bookDao.findByPage(start,step);
		System.out.println(books.getContent());
		model.addAttribute("books",books);
		model.addAttribute("bucher",bucher);
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

	@Override
	public List<Book> findBySearch(String search) {
		return bookDao.findBySearch(search);
	}

	


}
