/**
 * 
 */
package com.demo.service;

import com.demo.bean.User;

/**
 * @author Addo
 *
 */
public interface UserService {
	
	public User loadUserByUsernameAndPassword(User user);
	
	public User loadUserByUsername(String username);

	/**
	 * @param user
	 * @return
	 */
	public boolean update(User user);
}
