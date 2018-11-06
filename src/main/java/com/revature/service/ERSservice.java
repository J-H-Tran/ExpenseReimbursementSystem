package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;

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
	public boolean isValidEmplLogin(String username, String password) {
		return EmpImpDao.getImpDao().doLogin(username, password);
		
	}
	public void logoutEmpl() {
		
	}
	//send reimbursement request
	public boolean sendReimbRqst(Employee employee, Reimbursement reimbursement){
		return EmpImpDao.getImpDao().postReimbRqst(employee, reimbursement);
	}
	//view reimbursement status
	public ArrayList<Reimbursement> checkPendingReimb(Employee employee){
		return EmpImpDao.getImpDao().getPendingReimb(employee);
		
	}
	public ArrayList<Reimbursement> checkApprovedReimb(Employee employee){
		return EmpImpDao.getImpDao().getApprovedReimb(employee);
		
	}
	public ArrayList<Reimbursement> checkAllReimb(Employee employee){
		return EmpImpDao.getImpDao().getAllReimb(employee);
		
	}
	//viewEmpInfo
	public Employee seeEmplInfo(String username){
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
	public ArrayList<Employee> getAllPendingReimbRqst() {
		return null;
		
	}
	//approve/deny requests
	public void handleReimbRqst() {
		
	}
	//view resolved requests and the approving manager
	public ArrayList<Employee> getAllResolvedReimbRqst() {
		return null;
	}
	//view all employees
	public ArrayList<Employee> getAllEmpl() {
		return null;
	}
	//view an employee
	public Employee getEmpl() {
		return null;
	}
	
}
