package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface EmpDao {
	
	public boolean doLogin(String username, String password) throws SQLException;
	public void doLogout();
	public boolean postReimbRqst(Employee employee, Reimbursement reimbursement) throws SQLException;
	public List<String> getPendingReimb(Employee employee) throws SQLException;
	public List<String> getApprovedReimb(Employee employee) throws SQLException;
	public Employee getEmpInfo(String username) throws SQLException;
	public boolean updateEmpInfo(Employee employee);

}
