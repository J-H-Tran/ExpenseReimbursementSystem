package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface MngrDao {
	
	public boolean doLogin(String username);
	public void doLogout();
	public Employee getEmpInfo(int emplWorkID);
	public ArrayList<Employee> getAllEmpInfo();
	public ArrayList<Reimbursement> getResolvingMngr();
	public boolean resolveReimbRqst(Employee employee, Reimbursement reimbursement);
	public ArrayList<Reimbursement> getPendingReimb();
	
}
