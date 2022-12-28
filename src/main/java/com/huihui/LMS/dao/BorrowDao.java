package com.huihui.LMS.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huihui.LMS.pojo.Borrow;

public interface BorrowDao extends JpaRepository<Borrow, Integer>{

	List<Borrow> getAll();

}
