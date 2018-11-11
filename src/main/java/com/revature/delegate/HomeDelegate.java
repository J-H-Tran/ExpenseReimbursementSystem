package com.revature.delegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Login;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.ERSservice;

public class HomeDelegate {
	public void goHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Get our session information
		HttpSession session = req.getSession();
		// Give a personalized response
		Login login = (Login) session.getAttribute("user");

		if (login == null) {
			//resp.sendRedirect("login");
			resp.sendRedirect("/ExpenseReimbursementSystem/static/index.html");
		} 
		else {
			PrintWriter pw = resp.getWriter();
			pw.write("<!DOCTYPE html><html><head>" + "<meta charset=\"ISO-8859-1\"><title>HelloWorld</title>"
					+ "</head><body>");

			pw.write("<div><div style=\"background-color:" + login.getFavColor() + "\">" + "<h4>Hello "
					+ login.getUsername() + "</h4></div>" + "<form action=\"logout\" method=\"post\">"
					+ "<input type=\"submit\" value=\"Logout\"/>" + "</form></div>");

			pw.write("</body></html>");
		}
	}
	public void getEmpInfo(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Employee emplInfo = new Employee();
		ObjectMapper mapper = new ObjectMapper();
		Employee empl = (Employee) req.getSession().getAttribute("user");
		emplInfo = ERSservice.getERSservice().seeEmplInfo(empl.getUsrName());
		resp.setHeader("Content-Type", "application/json"); //tells html that it's expecting content type of json
		mapper.writeValue(resp.getOutputStream(), emplInfo); //writes data to html
	}
	/*Employee empl = (Employee) req.getSession().getAttribute("user");
	ObjectMapper mapper = new ObjectMapper();
	resp.setHeader("Content-Type", "application/json"); //tells html that it's expecting content type of json
	mapper.writeValue(resp.getOutputStream(), empl); //writes data to html
*/	public void fillAllReimb(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Employee loginEmpl = (Employee) req.getSession().getAttribute("user");
		ArrayList<Reimbursement> reimbList = ERSservice.getERSservice().checkAllReimb(loginEmpl);
		resp.setHeader("Content-Type", "application/json");
		mapper.writeValue(resp.getOutputStream(), reimbList);
	}

	public void sendReimbRqst(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		//ObjectMapper mapper = new ObjectMapper();
		Reimbursement rs = new Reimbursement();
		rs.setReimbType(req.getParameter("type"));
		rs.setReimbCost(Double.parseDouble(req.getParameter("cost")));
;		Employee loginEmpl = (Employee)req.getSession().getAttribute("user");
		ERSservice.getERSservice().sendReimbRqst(loginEmpl, rs);
		resp.sendRedirect("./static/ers_employee.html");
	}
//	private int emplID;
//	private int jobID;
//	private String jobDescr;
//	private String firName;
//	private String lasName;
//	private String emailAddr;
//	private String usrName;
//	private String passWord;
	public void updateEmplInfo(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		//ObjectMapper mapper = new ObjectMapper();
		Employee emp = new Employee();
		Employee newUser = (Employee) req.getSession().getAttribute("user");
		emp.setEmplID(newUser.getEmplID());
		emp.setFirName(req.getParameter("fname"));
		emp.setLasName(req.getParameter("lname"));
		emp.setEmailAddr(req.getParameter("email"));
		emp.setUsrName(req.getParameter("uname"));
		emp.setPassWord(newUser.getPassWord());
		emp.setJobDescr(newUser.getJobDescr());
		emp.setJobID(newUser.getJobID());
		//Employee empl = mapper.readValue(req.getReader(), Employee.class);
		//Employee loginEmpl = (Employee)req.getSession().getAttribute("user");
		System.out.println(newUser);
		ERSservice.getERSservice().pushEmplInfo(emp, newUser);
		req.getSession().removeAttribute("user");
		req.getSession().setAttribute("user", emp);
		resp.sendRedirect("./static/ers_employee.html");
	}
// **************************************** Manager features ****************************************
	public void mngrGetAllEmpl(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Employee> empList = ERSservice.getERSservice().getAllEmpl();
		resp.setHeader("Content-Type", "application/json");
		mapper.writeValue(resp.getOutputStream(), empList);
	}
	
	public void mngrGetAllPend(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Reimbursement> apprMngrReimb = ERSservice.getERSservice().getAllPendingReimbRqst();
		resp.setHeader("Content-Type", "application/json");
		mapper.writeValue(resp.getOutputStream(), apprMngrReimb);
	}
	
	public void mngrGetApprMngr(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Reimbursement> apprMngrReimb = ERSservice.getERSservice().getAllResolvedReimbRqst();
		resp.setHeader("Content-Type", "application/json");
		mapper.writeValue(resp.getOutputStream(), apprMngrReimb); // sends to .js
	}

	public void mngrGetReimbRqst(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Employee empl = mapper.readValue(req.getReader(), Employee.class);
		ArrayList<Reimbursement> empList = ERSservice.getERSservice().getEmpReimb(empl);
		resp.setHeader("Content-Type", "application/json");
		mapper.writeValue(resp.getOutputStream(), empList); // .js gets this as a response
	}

	public void resolveRqst(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		//ObjectMapper mapper = new ObjectMapper();
		Employee loginMngr = (Employee) req.getSession().getAttribute("user");
		Reimbursement rslvReimb = new Reimbursement();
		rslvReimb.setReimbID(Integer.parseInt(req.getParameter("rID")));
		rslvReimb.setReimbStatus(req.getParameter("resolv"));
		//Employee loginEmpl = (Employee)req.getSession().getAttribute("user");
		ERSservice.getERSservice().handleReimbRqst(loginMngr, rslvReimb);
		resp.sendRedirect("./static/ers_manager.html");
	}
	



}
