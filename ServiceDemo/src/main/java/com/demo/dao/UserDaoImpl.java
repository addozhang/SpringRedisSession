/**
 * 
 */
package com.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.bean.User;

/**
 * @author Addo
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.dao.UserDao#loadUserByUsername(java.lang.String)
	 */
	public User loadUserByUsernameAndPassword(User user) {
		Session session = sessionFactory.openSession();
		List<User> list = session
				.createQuery("from User where username=? and password=?")
				.setParameter(0, user.getUsername())
				.setParameter(1, user.getPassword()).list();
		try {
			if (list.size() == 0)
				return null;
			else
				return list.get(0);
		} finally {
			session.close();
		}
	}

	public User loadUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		List<User> list = session.createQuery("from User where username=?")
				.setParameter(0, username).list();
		try {
			if (list.size() == 0)
				return null;
			else
				return list.get(0);
		} finally {
			session.close();
		}
	}

	public void saveUser(User user) {
		Session session = sessionFactory.openSession();
		try {
			session.saveOrUpdate(user);
		} finally {
			session.close();
		}
	}
}
