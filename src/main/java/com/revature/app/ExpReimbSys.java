package com.revature.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.ERSservice;

public class ExpReimbSys {
    public static void main( String[] args ) throws SQLException {
    	/*List<String> arrList = new ArrayList<>();
//        Employee employee = new Employee(1, 3000, "employee", "j", "t", "jt@email.com", "jt", "jt1");
//        Reimbursement reimbursement = new Reimbursement(6, 1, "foood", 1000, "approved", 1);
        //System.out.println(ERSservice.getUserService().isValidEmplLogin("jt"));
    	//System.out.println(ERSservice.getERSservice().getEmplInfo("jt"));
    	//System.out.println("this");
    	//System.out.println(ERSservice.getERSservice().getEmplInfo("jt").getJobDescr());
    	//System.out.println(ERSservice.getERSservice().postReimbRqst(employee, reimbursement));
        
        Employee empl = new Employee();
    	Reimbursement reimb = new Reimbursement();
//		empl.setEmplID(1);
//		reimb.setReimbID(5);
//		reimb.setReimbType("travel");
//		reimb.setReimbCost(1000);
//		System.out.println(ERSservice.getERSservice().postReimbRqst(empl, reimb));
    	
    	empl.setEmplID(1);
    	//arrList = ERSservice.getERSservice().checkReimbStatus(empl);
    	//System.out.println(ERSservice.getERSservice().checkApprovedReimb(empl));
    	for (String str: ERSservice.getERSservice().checkApprovedReimb(empl)) {
    		System.out.print(str);
    	}
    	for (String str: ERSservice.getERSservice().checkPendingReimb(empl)) {
    		System.out.print(str);
    	}*/
    	
    }
}
