package com.service;

import java.util.List;

import com.bean.User;

public interface UserService
{
	public String saveOrUpdateUser(User user);
	
	public String isLogin(String username, String password);
	
	public List<User> getUser(int page);
	
	public int getUserCount();
	
	public String deleteUser(int id);
	
	public User getSingleUser(String username);
}
