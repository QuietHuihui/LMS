package com.huihui.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huihui.ems.pojo.Book;

public interface BookDao extends JpaRepository<Book, Integer>{

}
