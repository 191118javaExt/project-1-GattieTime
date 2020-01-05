package com.revature.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginTemplate;
import com.revature.models.ReimbursementTemplate;
import com.revature.models.User;
import com.revature.services.ReinbursService;
import com.revature.services.UserService;

public class HelperUtil {
	
	private static ObjectMapper om = new ObjectMapper();

	public static void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String body = readReq(req, res);
		
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

	public static void newReim(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String body = readReq(req, res);
		ReimbursementTemplate rt = om.readValue(body, ReimbursementTemplate.class);
		
		if (ReinbursService.newReimburs(rt)) {
			res.setContentType("application/json");
			res.setStatus(201);
		}
		
	}
	
	private static String readReq(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		return body;
	}

}
