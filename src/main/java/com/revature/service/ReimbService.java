package com.revature.service;

public class ReimbService {
	private static ReimbService reimbService;
	private ReimbService() {
	}
	public static ReimbService getUserService() {
		if(reimbService == null) {
			reimbService = new ReimbService();
		}
		return reimbService;
	}
	//EMPLOYEE DAO
	//verify user name and password
	//send reimbursement request
	//view reimbursement status
	//viewEmpInfo
	//updateEmpInfo

	//MANAGER DAO
	//login
	//logout
	//view all pending requests
	//approve/deny requests
	//view resolved requests and the approving manager
	//view all employees
	//view an employee
	
}
