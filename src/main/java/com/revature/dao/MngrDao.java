package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface MngrDao {
	
	public boolean doLogin(String username);
	public void doLogout();
	public Employee getEmpInfo(int emplWorkID);
	public List<String> getAllEmpInfo();
	public List<String> getResolvingMngr();
	public void resolveReimbRqst();
	public List<String> getPendingReimb();
	
}
