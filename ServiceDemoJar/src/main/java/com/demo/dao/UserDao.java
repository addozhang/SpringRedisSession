package com.demo.dao;

import com.demo.bean.User;

public interface UserDao {

	public abstract User loadUserByUsernameAndPassword(User user);

	public abstract void saveUser(User user);
	
	public abstract User loadUserByUsername(String username);

}