package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.revature.dao.EmpImpDao;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.ERSservice;

class servicetest {
	

	@Test
	//Test employee login
	void test() throws SQLException {
		assertTrue(ERSservice.getERSservice().isValidEmplLogin("jt", "jt1"));
		
	}
	@Test
	//Test employee information retreival
	void test1() throws SQLException {
		Employee empl = new Employee();
		empl.setJobDescr("employee");
		assertEquals(empl.getJobDescr(), ERSservice.getERSservice().seeEmplInfo("jt").getJobDescr());
	}
	@Test
	//Test reimbursement request submission
	void test2() throws SQLException {
		Employee empl = new Employee();
    	Reimbursement reimb = new Reimbursement();
		empl.setEmplID(1);
		reimb.setReimbID(100);
		reimb.setReimbType("travel");
		reimb.setReimbCost(1000);
		assertTrue(ERSservice.getERSservice().sendReimbRqst(empl, reimb));
	}

}
