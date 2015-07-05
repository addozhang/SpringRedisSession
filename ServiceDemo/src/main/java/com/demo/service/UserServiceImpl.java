/**
 * 
 */
package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.User;
import com.demo.dao.UserDao;

/**
 * @author Addo
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User loadUserByUsernameAndPassword(User user) {
		return userDao.loadUserByUsernameAndPassword(user);
	}

	public boolean update(User user) {
		userDao.saveUser(user);
		return true;
	}

	@Override
	public User loadUserByUsername(String username) {
		return userDao.loadUserByUsername(username);
	}

}
