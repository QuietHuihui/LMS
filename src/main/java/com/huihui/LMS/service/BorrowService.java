package com.huihui.LMS.service;

import java.util.List;

import com.huihui.LMS.pojo.Borrow;

public interface BorrowService {

	void save(Borrow borrow);

	List<Borrow> getAllBorrow();

	List<Borrow> getBorrow(Integer id);

}
