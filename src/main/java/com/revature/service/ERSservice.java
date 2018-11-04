package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.EmpImpDao;
import com.revature.model.Employee;
//import com.revature.model.Manager;
import com.revature.model.Reimbursement;

public class ERSservice {
	private static ERSservice reimbService;
	
	private ERSservice() {
	}
	public static ERSservice getERSservice() {
		if(reimbService == null) {
			reimbService = new ERSservice();
		}
		return reimbService;
	}
	//EMPLOYEE DAO
	//verify user name and password
	public boolean isValidEmplLogin(String username, String password) throws SQLException {
		return EmpImpDao.getImpDao().doLogin(username, password);
		
	}
	public void logoutEmpl() {
		
	}
	//send reimbursement request
	public boolean sendReimbRqst(Employee employee, Reimbursement reimbursement) throws SQLException {
		return EmpImpDao.getImpDao().postReimbRqst(employee, reimbursement);
	}
	//view reimbursement status
	public List<String> checkPendingReimb(Employee employee) throws SQLException {
		return EmpImpDao.getImpDao().getPendingReimb(employee);
		
	}
	public List<String> checkApprovedReimb(Employee employee) throws SQLException {
		return EmpImpDao.getImpDao().getApprovedReimb(employee);
		
	}
	//viewEmpInfo
	public Employee seeEmplInfo(String username) throws SQLException {
		return EmpImpDao.getImpDao().getEmpInfo(username);
		
	}
	//updateEmpInfo
	public void pushEmplInfo() {
		
	}
	
	//MANAGER DAO
	//login
	public boolean isValidMngrLogin() {
		return false;
	}
	//logout
	public void logoutMngr() {
		
	}
	//view all pending requests
	public List<String> getAllPendingReimbRqst() {
		return null;
		
	}
	//approve/deny requests
	public void handleReimbRqst() {
		
	}
	//view resolved requests and the approving manager
	public List<String> getAllResolvedReimbRqst() {
		return null;
	}
	//view all employees
	public List<String> getAllEmpl() {
		return null;
	}
	//view an employee
	public Employee getEmpl() {
		return null;
	}
	
}
