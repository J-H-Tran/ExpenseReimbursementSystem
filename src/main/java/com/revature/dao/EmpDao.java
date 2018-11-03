package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmpDao {
	
	public boolean doLogin(String username, String password);
	public void doLogout();
	public boolean postReimbRqst(Employee employee, Reimbursement reimbursement);
	public List<String> getPendingReimb(Employee employee);
	public List<String> getApprovedReimb(Employee employee);
	public Employee getEmpInfo(String username);
	public boolean updateEmpInfo(Employee employee);

}
