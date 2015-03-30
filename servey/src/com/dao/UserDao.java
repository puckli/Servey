package com.dao;

import com.bean.User;

public interface UserDao
{
	public void saveUser(User user);
	
	public User getSingleUser(String username);
}
