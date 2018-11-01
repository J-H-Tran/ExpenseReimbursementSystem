package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.dao.EmpImpDao;
import com.revature.model.Employee;
import com.revature.service.ERSservice;

class servicetest {
	

	@Test
	void test() {
		assertTrue(ERSservice.getERSservice().isValidEmplLogin("jt"));
		
	}
	@Test
	void test1() {
		Employee empl = new Employee();
		empl.setJobDescr("employee");
		assertEquals(empl.getJobDescr(), ERSservice.getERSservice().getEmplInfo("jt").getJobDescr());
	}

}
