package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface MngrDao {
	
	public boolean doLogin(String username);
	public void doLogout();
	public Employee getEmpInfo(String username);
	public List<String> getAllEmpInfo();
	public List<String> getResolvingMngr();
	public void resolveReimbRqst();
	public List<String> getPendingReimb(Employee employee);
	
//	public boolean doLogin(String username);
//	public void doLogout();
//	public boolean postReimbRqst(Employee employee, Reimbursement reimbursement);
//	public List<String> getPendingReimb(Employee employee);
//	public List<String> getApprovedReimb(Employee employee);
//	public Employee getEmpInfo(String username);
//	public void updateEmpInfo();
}
