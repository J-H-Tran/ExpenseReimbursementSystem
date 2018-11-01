package com.revature.app;

import com.revature.service.ERSservice;

public class ExpReimbSys {
    public static void main( String[] args ) {
        
        //System.out.println(ERSservice.getUserService().isValidEmplLogin("jt"));
    	System.out.println(ERSservice.getERSservice().getEmplInfo("jt"));
    	System.out.println("this");
    	System.out.println(ERSservice.getERSservice().getEmplInfo("jt").getJobDescr());
    }
}
