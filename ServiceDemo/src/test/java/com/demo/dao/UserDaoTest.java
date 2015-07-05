package com.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-config.xml",
		"classpath:spring/mvc-config.xml" })
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void testLoadUserByUsername() {
		
	}

	@Test
	public void testSave() {
		
	}

}
