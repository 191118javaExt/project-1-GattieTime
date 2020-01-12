package com.revature.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ApprovalTemplate;
import com.revature.models.FindReimTemplate;
import com.revature.models.LoginTemplate;
import com.revature.models.NewUserTemplate;
import com.revature.models.ReceiptTemplate;
import com.revature.models.ReimbursementTemplate;
import com.revature.models.Reinbursement;
import com.revature.models.User;
import com.revature.models.UserIdTemplate;
import com.revature.repositories.ReinburseDAO;
import com.revature.repositories.ReinburseDAOImpl;
import com.revature.services.ReinbursService;
import com.revature.services.UserService;

public class HelperUtil {

	private static ObjectMapper om = new ObjectMapper();

	public static void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String body = readReq(req, res);

		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class);

		String username = loginAttempt.getUsername();
		String password = String.valueOf(loginAttempt.getPassword().hashCode());

		// logger.info("User attempted to log in with username: " + username);
		User u = UserService.confirmLogin(username, password);
		if (u != null) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			out.println(om.writeValueAsString(u));

			// logger.info(username+" has successfully logged in.");
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

	private static String readReq(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		return body;
	}

	public static void getUserReim(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String body = readReq(req, res);
		UserIdTemplate u = om.readValue(body, UserIdTemplate.class);

		List<Reinbursement> result = ReinbursService.getUserReimburs(u);

		if (result != null) {
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			out.println(om.writeValueAsString(result));
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}

	}

	public static void storeReceipt(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String body = readReq(req, res);
		ReceiptTemplate rt = om.readValue(body, ReceiptTemplate.class);

		if (ReinbursService.newReceipt(rt)) {
			res.setContentType("application/json");
			res.setStatus(201);
		}

	}

	public static void getReim(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String body = readReq(req, res);
		FindReimTemplate frt = om.readValue(body, FindReimTemplate.class);
		
		List<Reinbursement> result = ReinbursService.getReimburs(frt);

		if (result != null) {
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			out.println(om.writeValueAsString(result));
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}

	public static void approve(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String body = readReq(req, res);
		ApprovalTemplate at = om.readValue(body, ApprovalTemplate.class);
		
		ReinburseDAO rDAO = new ReinburseDAOImpl();
		Reinbursement r = rDAO.findById(at.getReimId());
		
		if (r.getAuthor() == at.getApprover()) {
			res.setContentType("application/json");
			res.setStatus(403);
		} else {
			if(ReinbursService.approval(at, r)) {
				res.setContentType("application/json");
				res.setStatus(202);
			} else {
				res.setContentType("application/json");
				res.setStatus(500);
			}
		}
		
		
	}

	public static void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String body = readReq(req, res);
		NewUserTemplate nut = om.readValue(body, NewUserTemplate.class);
		System.out.println(nut);
		
		if(UserService.newUser(nut)) {
			res.setContentType("application/json");
			res.setStatus(201);
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
		
	}

}
