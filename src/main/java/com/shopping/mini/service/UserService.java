package com.shopping.mini.service;

import com.shopping.mini.model.User;
import com.shopping.mini.repository.impl.UserRepositoryImpl;
import com.shopping.mini.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepositoryImpl userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	DateUtil dateUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}

	public User addUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.addUser(user);
	}

	public User updateUser(User user) {
		user.setUpdatedDate(dateUtil.getDateTime());
		return userDao.updateUser(user);
	}

}