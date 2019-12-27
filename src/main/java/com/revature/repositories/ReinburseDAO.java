package com.revature.repositories;

import java.util.List;

import com.revatute.models.Reinbursement;

public interface ReinburseDAO {
	public List<Reinbursement> findAll();
	public Reinbursement findById(int id);
	public boolean updateReinburse(Reinbursement r);
	public boolean insertReinburse(Reinbursement r);
}
