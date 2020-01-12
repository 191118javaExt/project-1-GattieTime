package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.NewUserTemplate;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {

	private static Logger logger = LogManager.getLogger(UserService.class);

	public static User confirmLogin(String username, String password) {
		User u = null;
		
		UserDAO uDAO = new UserDAOImpl();
		u = uDAO.findByName(username);
		
		if (u == null) {
			u = uDAO.findByEmail(username);
		}
		if (u != null) {
			if (u.getPassword().equals(password) == false) {
				u = null;
				logger.info("User using username " + username + " failed to log in.");
			}
		} else {
			logger.info("User tried to log in with user name "+username+" that does not exist.");
		}
		System.out.println(u);
		return u;
	}

	public static boolean newUser(NewUserTemplate nut) {
		User u = new User(nut.name, String.valueOf(nut.password.hashCode()), nut.firstName, nut.lastName, nut.email, 1);
		System.out.println(u);
		UserDAO uDAO = new UserDAOImpl();
		if(uDAO.insertUser(u)) {
			return true;
		}else {return false;}
	}

}
