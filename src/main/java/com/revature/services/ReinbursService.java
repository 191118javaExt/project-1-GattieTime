package com.revature.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.ReimbursementTemplate;
import com.revature.models.Reinbursement;
import com.revature.models.User;
import com.revature.repositories.ReinburseDAO;
import com.revature.repositories.ReinburseDAOImpl;

public class ReinbursService {
	private static Logger logger = LogManager.getLogger(ReinbursService.class);
	
	public static boolean reinbursResolve(User u, Reinbursement r, int i) { 
		r.setStatus(i);
		r.setResolver(u.getId());
		LocalDateTime dt = LocalDateTime.now();
		r.setResolved(Timestamp.valueOf(dt));
		ReinburseDAOImpl rDAO = new ReinburseDAOImpl();
		if(rDAO.updateReinburse(r)) {
			logger.info("Reinbursement " +r.getId()+" was resolved by user "+u.getId()+".");
			return true;
		} else {
			logger.info("Reinbursement " +r.getId()+" failed to be updated.");
			return false;
		}
	}

	public static boolean newReimburs(ReimbursementTemplate rt) {
		Reinbursement r = new Reinbursement (rt.getAmount(), 
				Timestamp.valueOf(LocalDateTime.now()), 
				rt.getDescription(), 
				null, 
				rt.getUserId(),
				1, 
				rt.getType());
		
		ReinburseDAO rDAO = new ReinburseDAOImpl();
		if(rDAO.insertReinburse(r)) {
			return true;
		} else {
			return false;
		}
		
		
		
	}
	
	

}
