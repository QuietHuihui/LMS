package com.huihui.LMS.service;

import com.huihui.LMS.pojo.User;

public interface UserService {

	void register(User user);

	User login(String username, String password);

}
