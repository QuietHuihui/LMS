package com.huihui.LMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

	@RequestMapping("/addborrow/{id}")
	public String addBorrow(@PathVariable Integer id) {
		return null;
	}

}
