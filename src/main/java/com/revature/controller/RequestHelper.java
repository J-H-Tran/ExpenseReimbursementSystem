package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.delegate.HomeDelegate;
import com.revature.delegate.LoginDelegate;
import com.revature.model.Employee;

public class RequestHelper {
	private HomeDelegate hd = new HomeDelegate();
	private LoginDelegate ld = new LoginDelegate();
	//private ManagerDelegate
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String switchString = req.getRequestURI().substring(req.getContextPath().length() + 1);
		
		while(switchString.indexOf("/") > 0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		switch(switchString) {
		case "home": 
			hd.goHome(req, resp); 
			break;
		case "login": 
			if("POST".equals(req.getMethod())) {
				ld.login(req, resp);
			} 
			else {
				ld.getPage(req, resp);
			} 
			break;
		case "logout": 
			ld.logout(req, resp); 
			break;
		case "employee-info": 
			Employee empl = (Employee) req.getSession().getAttribute("user");
			ObjectMapper mapper = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json"); //tells html that it's expecting content type of json
			mapper.writeValue(resp.getOutputStream(), empl); //writes data to html
			break;
		case "employee-reimbursements":
			hd.fillAllReimb(req, resp);
			break;
		case "employee-submit-reimb":
			hd.sendReimbRqst(req, resp);
		default: 
			break;
		}
	}
}
