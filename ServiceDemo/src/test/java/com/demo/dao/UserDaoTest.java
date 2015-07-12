package com.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-config.xml",
		"classpath:spring/mvc-config.xml" })
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	public void testLoadUserByUsername() {
		
	}

	@Test
	public void testSave() {
		User user = userDao.loadUserByUsername("name1");
		user.setPassword(encoder.encode("pass3"));
		userDao.saveUser(user);
	}

}
