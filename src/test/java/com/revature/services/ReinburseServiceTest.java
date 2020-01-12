package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.models.ApprovalTemplate;
import com.revature.models.FindReimTemplate;
import com.revature.models.ReceiptTemplate;
import com.revature.models.ReimbursementTemplate;
import com.revature.models.Reinbursement;
import com.revature.models.User;
import com.revature.models.UserIdTemplate;
import com.revature.repositories.ReinburseDAOImpl;

public class ReinburseServiceTest {
	
	ReinburseDAOImpl rDAOMock = mock(ReinburseDAOImpl.class);
	ReinbursService rsMock = new ReinbursService(rDAOMock);
	
	@Test
	public void testReinbursResolve() {
		User u = new User();
		Reinbursement rtest = new Reinbursement(1, 0, null, null, "test", "test", 2, 3, 4);
		when(rDAOMock.updateReinburse(rtest)).thenReturn(true);
		assertEquals(true, rsMock.reinbursResolve(u, rtest, 2));
	}
	
	@Test
	public void testNewReimburs() {
		ReimbursementTemplate rt = new ReimbursementTemplate();
		Reinbursement rtest = new Reinbursement();
		when(rDAOMock.insertReinburse(rtest)).thenReturn(true);
		assertEquals(false, rsMock.newReimburs(rt));
	}
	
	@Test
	public void testGetUserReimburs() {
		
		Reinbursement r1 = new Reinbursement();
		Reinbursement r2 = new Reinbursement();
		Reinbursement r3 = new Reinbursement();
		List<Reinbursement> rl = new ArrayList<Reinbursement>();
		rl.add(r1);
		rl.add(r3);
		rl.add(r2);
		UserIdTemplate u = new UserIdTemplate(1);
		when(rDAOMock.findByUserId(1)).thenReturn(rl);
		assertEquals(3, rsMock.getUserReimburs(u).size());
	}
	
	@Test
	public void testNewReceipt() {
		ReceiptTemplate rt = new ReceiptTemplate(1,"test");
		when(rDAOMock.updateReceipt(rt)).thenReturn(true);
		assertTrue(rsMock.newReceipt(rt));
	}
	
	@Test
	public void testGetReimbursAll() {

		Reinbursement r1 = new Reinbursement();
		Reinbursement r2 = new Reinbursement();
		Reinbursement r3 = new Reinbursement();
		List<Reinbursement> rl = new ArrayList<Reinbursement>();
		rl.add(r1);
		rl.add(r3);
		rl.add(r2);
		FindReimTemplate frt = new FindReimTemplate();
		frt.setStatus(1);
		when(rDAOMock.findAll()).thenReturn(rl);
		assertEquals(3, rsMock.getReimburs(frt).size());
	}
	
	@Test
	public void testApproval() {
		ApprovalTemplate at = new ApprovalTemplate();
		Reinbursement r = new Reinbursement();
		
		when(rDAOMock.updateReinburse(r)).thenReturn(true);
		assertTrue(rsMock.approval(at, r));
	}

}
