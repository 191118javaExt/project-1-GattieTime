package com.revature.repositories;

import java.util.List;

import com.revature.models.ReceiptTemplate;
import com.revature.models.Reinbursement;

public interface ReinburseDAO {
	public List<Reinbursement> findAll();
	public List<Reinbursement> findByUserId(int userId);
	public Reinbursement findById(int id);
	public boolean updateReinburse(Reinbursement r);
	public boolean insertReinburse(Reinbursement r);
	public boolean updateReceipt(ReceiptTemplate rt);
	public List<Reinbursement> findByStatus(int status);
}
