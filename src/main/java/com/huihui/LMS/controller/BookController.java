package com.huihui.LMS.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huihui.LMS.dao.BookDao;
import com.huihui.LMS.dao.CategoryDao;
import com.huihui.LMS.pojo.Book;
import com.huihui.LMS.pojo.Category;
import com.huihui.LMS.service.BookService;
import com.huihui.LMS.wrapper.CategoryWrapper;

@Controller
@RequestMapping("/book")
public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	private final Path root = Paths.get("photo");
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/addbook")
	public String addBook(Book book,HttpSession session,String tcategory,MultipartFile photo) {
		
		//把类别传入到session中，便于前端展示下拉菜单
		List<CategoryWrapper>categories = categoryDao.getAllCat();
		session.setAttribute("categories",categories);

		
		//categoryID不能为空
		if(!(tcategory==null)) {
			
		//根据选择的类别id从数据库中找到相应的Category实体
		Optional<Category> option = categoryDao.findById(Integer.parseInt(tcategory));
		Category bookcat;
		bookcat = option.get();
		
		//设置书本的Category
		book.setCategory(bookcat);
		
		//设置保存图片的名称
		//获取文件原始的名称
		String originalFilename = photo.getOriginalFilename();
		//以精确到毫秒的时间作为新的文件名
		String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		//获取文件类型字符串
		String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		//拼接得到新的文件名
		String newFilename = fileNamePrefix+fileNameSuffix;
		
		//创建保存文件的目录（初次使用请手动在jar包目录下创建"photo"文件夹）
//	    try {
//	        Files.createDirectories(root);
//	      } catch (IOException e) {
//	        throw new RuntimeException("Could not initialize folder for upload!");
//	      }
		
	    //保存图片到项目根目录下的"photo"目录下面
		try {
			Files.copy(photo.getInputStream(), this.root.resolve(newFilename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//设置封面文件名
		book.setCover(newFilename);
		//把提交的书本对象存到数据库中
		bookService.save(book);
		}
		return "addbook";
	}
	
	@GetMapping("/getbook")
	public String getBook(Model model) {
		bookService.getBook(model);
		return "book";
	}
	
	@GetMapping("/delete/{id}")
	@ResponseBody
	public String deleteCat(@PathVariable("id")Integer id) {
		try {
			bookService.deleteByID(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return "false";
		}
		return "true";
	}
	
}
