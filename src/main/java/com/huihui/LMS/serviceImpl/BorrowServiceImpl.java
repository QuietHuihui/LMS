package com.huihui.LMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huihui.LMS.dao.BorrowDao;
import com.huihui.LMS.pojo.Borrow;
import com.huihui.LMS.service.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService{

	@Autowired
	BorrowDao borrowDao;
	
	@Override
	public void save(Borrow borrow) {
		try {
			borrowDao.save(borrow);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public List<Borrow> getAllBorrow() {
		try {
			return borrowDao.getAll();			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
