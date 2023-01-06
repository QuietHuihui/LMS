package com.huihui.ems.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.huihui.ems.dao.BookDao;
import com.huihui.ems.dao.CategoryDao;
import com.huihui.ems.pojo.Book;
import com.huihui.ems.pojo.Category;
import com.huihui.ems.service.BookService;
import com.huihui.ems.wrapper.CategoryWrapper;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Value("${photo.file.dir}")
	private String path;
	
	@Override
	public void addBook(Book book,HttpSession session,MultipartFile photo,String tname) {
		
		List<CategoryWrapper>categories = categoryDao.getAllCat();
		session.setAttribute("categories",categories);
		String originalFileName = photo.getOriginalFilename();
		String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String fileNameSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = fileNamePrefix+fileNameSuffix;
		
		Category bookcat = categoryDao.findByName(tname);
		
		book.setCategory(bookcat);
		
		try {
			photo.transferTo(new File(path,newFileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//bookDao.save(book);
	}

}
