package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmpDao {
	
	public boolean doLogin(String username, String password);
	public void doLogout();
	public boolean postReimbRqst(Employee employee, Reimbursement reimbursement);
	public ArrayList<Reimbursement> getPendingReimb(Employee employee);
	public ArrayList<Reimbursement> getApprovedReimb(Employee employee);
	public Employee getEmpInfo(String username);
	public boolean updateEmpInfo(Employee employee, Employee logged);

}
