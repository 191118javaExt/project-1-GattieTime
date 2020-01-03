package com.revature.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginTemplate;
import com.revature.models.User;

public class LoginService {
	
	private static ObjectMapper om = new ObjectMapper();

	public static void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class);
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();
		
		//logger.info("User attempted to log in with username: " + username);
		User u = UserService.confirmLogin(username, password);
		if (u != null) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			out.println(om.writeValueAsString(u));
			
			//logger.info(username+" has successfully logged in.");
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
		
	}
	

}
