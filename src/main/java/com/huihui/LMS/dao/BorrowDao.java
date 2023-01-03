package com.huihui.LMS.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.huihui.LMS.pojo.Borrow;

public interface BorrowDao extends JpaRepository<Borrow, Integer>{

	List<Borrow> getAll();

	@Query("select b from Borrow b where b.user.id=id ")
	List<Borrow> getBorrow(Integer id);

}
