package com.huihui.LMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huihui.LMS.pojo.Book;

public interface BookDao extends JpaRepository<Book, Integer>{

}
