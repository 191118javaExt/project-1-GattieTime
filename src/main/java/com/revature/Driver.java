package com.revature;

import com.revature.models.Reinbursement;
import com.revature.repositories.ReinburseDAO;
import com.revature.repositories.ReinburseDAOImpl;

public class Driver {

	public static void main(String[] args) {

		ReinburseDAO rDAO = new ReinburseDAOImpl();
		Reinbursement r = rDAO.findById(1);
		System.out.println(r.getReceipt());
		
	}
}
