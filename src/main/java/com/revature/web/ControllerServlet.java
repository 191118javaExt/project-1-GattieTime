package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.revature.services.LoginService;

public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 5562265021100328449L;
	
	
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String r = req.getParameter("r");
		
		switch(r.toLowerCase()) {
			case "login":
				LoginService.login(req, res);
				break;
		}
	}

}
