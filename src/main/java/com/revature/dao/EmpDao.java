package com.revature.dao;

import com.revature.model.Employee;

public interface EmpDao {
	
	public boolean doLogin(String username);
	public void doLogout();
	public void postReimbRqst();
	public void getReimbStatus();
	public Employee getEmpInfo(String username);
	public void updateEmpInfo();

}
