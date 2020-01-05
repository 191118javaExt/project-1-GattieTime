package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.revature.utils.HelperUtil;

public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 5562265021100328449L;
	
	
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		final String uri = req.getRequestURI().replace("/project-1/", "");
		
		
		
		switch(uri.toLowerCase()) {
			case "login":
				HelperUtil.login(req, res);
				break;
			case "newreim":
				HelperUtil.newReim(req, res);
				break;
			
		}
	}

}
