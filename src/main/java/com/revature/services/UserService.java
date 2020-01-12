package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.NewUserTemplate;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	
	
	public UserService() {
		super();
	}

	public UserService(UserDAO uDAO) {
		super();
		this.uDAO = uDAO;
	}

	static UserDAO uDAO = new UserDAOImpl();

	public User confirmLogin(String username, String password) {
		User u = null;
		
		
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
		return u;
	}

	public boolean newUser(NewUserTemplate nut) {
		User u = new User(nut.name, String.valueOf(nut.password.hashCode()), nut.firstName, nut.lastName, nut.email, 1);

		
		if(uDAO.insertUser(u)) {
			return true;
		}else {return false;}
	}

}
