package com.huihui.ems.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.huihui.ems.dao.CategoryDao;
import com.huihui.ems.pojo.Book;
import com.huihui.ems.pojo.Category;
import com.huihui.ems.service.BookService;
import com.huihui.ems.wrapper.CategoryWrapper;

@Controller
@RequestMapping("/book")
public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	private final Path root = Paths.get("photo");
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/addbook")
	public String addBook(Book book,HttpSession session,String tcategory,MultipartFile photo) {
		
		
		List<CategoryWrapper>categories = categoryDao.getAllCat();
		session.setAttribute("categories",categories);
		
		System.out.println("isbn: "+book.getIsbn()+"title: "+book.getTitle()+"author: "+book.getAuthor()+"amount: "+book.getAmount()
		+"tcategory: "+tcategory);
		
		Optional<Category> option = categoryDao.findById(Integer.parseInt(tcategory));
		Category bookcat;
		bookcat = option.get();
		
		book.setCategory(bookcat);
		System.out.println(photo.getOriginalFilename());
		
		String originalFilename = photo.getOriginalFilename();
		String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		String newFilename = fileNamePrefix+fileNameSuffix;
		System.out.println(root);
	    try {
	        Files.createDirectories(root);
	      } catch (IOException e) {
	        throw new RuntimeException("Could not initialize folder for upload!");
	      }
		//photo.transferTo(null)
		try {
			Files.copy(photo.getInputStream(), this.root.resolve(newFilename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addbook";
	}
}
