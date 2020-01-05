package com.revature;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class Driver {

	public static void main(String[] args) {

		UserDAO uDAO = new UserDAOImpl();
		User u = uDAO.findByName("testuser");
		System.out.println(u);
		
	}
}
