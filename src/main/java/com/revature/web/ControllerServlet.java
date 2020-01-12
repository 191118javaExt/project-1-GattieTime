package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.utils.HelperUtil;

public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 5562265021100328449L;
	
	public static final Logger logger = LogManager.getLogger(ControllerServlet.class);
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		final String uri = req.getRequestURI().replace("/project-1/", "");
		
		
		
		switch(uri.toLowerCase()) {
			case "login":
				logger.debug("login request recieved");
				HelperUtil.login(req, res);
				break;
			case "newreim":
				logger.debug("new reimbursement request recieved");
				HelperUtil.newReim(req, res);
				break;
			case "getuserreim":
				logger.debug("request for user reimbursement recieved");
				HelperUtil.getUserReim(req, res);
				break;
			case "receipt":
				logger.debug("new receipt request recieved");
				HelperUtil.storeReceipt(req, res);
				break;
			case "getreim":
				logger.debug("manager request for reimbursement recieved");
				HelperUtil.getReim(req, res);
				break;
			case "approve":
				logger.debug("approval request recieved");
				HelperUtil.approve(req, res);
				break;
			case "register":
				logger.debug("registration request recieved");
				System.out.println("Register");
				HelperUtil.register(req, res);
				break;
			
		}
	}

}
