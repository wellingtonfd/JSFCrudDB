package com.facade;

import com.model.User;
import com.dao.UserDAO;

public class UserFacade {
	private UserDAO userDao = new UserDAO();
	
	public User isValidLogin(String email, String password){
		userDao.beginTransaction();
		User user = userDao.findUserByEmail(email);
		
		if( user == null || !user.getPassword().equals(password)){
			return null;
		}
			
		return user;
	}
}
