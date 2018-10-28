package com.revature.dao;

public interface MngrDao {
	
	public void getEmpInfo();
	public void getAllEmpInfo();
	public void getResolvedReimbRqst();
	public void resolveReimbRqst();
	public void getPendingReimbs();
	public boolean tryLogin();
}
