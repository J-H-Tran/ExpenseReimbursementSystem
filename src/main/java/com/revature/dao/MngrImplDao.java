package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;
import com.revature.service.ERSservice;
import com.revature.util.ConnectionUtil;

public class MngrImplDao implements MngrDao{
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static MngrImplDao mngrDao;
	final static Logger log = Logger.getLogger(MngrImplDao.class);
	
	private MngrImplDao() {}
	public static MngrImplDao getMngrDao(){
		if (mngrDao == null){
			mngrDao = new MngrImplDao();
		}
		return mngrDao;
	}
// ******************************* BEGIN MAIN *************************************
	public static void main(String[] args) {
		Manager mngr = new Manager();
//		for (String str: getMngrDao().getAllEmpInfo()) {
//    		System.out.print(str);
//    	}
//		mngr.setUsrName("jt");
//		System.out.println(getMngrDao().doLogin(mngr.getUsrName()));
//		System.out.println();
//		for (String str: getMngrDao().getAllEmpInfo()) {
//    		System.out.print(str);
//    	}
//		System.out.println(getMngrDao().getEmpInfo(3000));
//		System.out.println();
//		for (String str: getMngrDao().getPendingReimb()) {
//    		System.out.print(str);
//    	}
//		for (String str: getMngrDao().getResolvingMngr()) {
//    		System.out.print(str);
//    	}
	}
// *******************************  END MAIN  *************************************
	@Override
	public boolean doLogin(String username) {
		String usrStr = "";
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try { 
			log.info("Manager attempted to login, check if user exists in database.");
			String sql = "SELECT * FROM empl_table WHERE e_USERNAME = ? and e_password = ? and e_position";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) { usrStr = rs.getString("m_USERNAME");
			}
			if (usrStr.equals(username)) {
				log.info("User exists in database");
				return true;
			}
		}catch (Exception e) {
			log.error("Exception in doLogin thrown");
			e.printStackTrace();
		}log.warn("User does not exist");
		return false;
	}
	@Override
	public void doLogout() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Employee getEmpInfo(int emplWorkID) {
		Employee empl = new Employee();
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try {
			log.info("Retreiving user info");
			String sql = "SELECT * FROM EMPL_TABLE WHERE E_job_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emplWorkID);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				log.info("did the while loop execute?");
				empl = new Employee(
						rs.getInt("E_ID"), 
						rs.getInt("E_JOB_ID"), 
						rs.getString("E_POSITION"), 
						rs.getString("E_FIRSTNAME"), 
						rs.getString("E_LASTNAME"), 
						rs.getString("E_EMAIL"), 
						rs.getString("E_USERNAME"), 
						rs.getString("e_password"));
			}
			return empl;
		} catch (SQLException s) {
			log.error("Exception in getUser thrown");
			s.getMessage();
		} finally {
			log.warn("getUser - executed finally block");
		}
		log.warn("Failed to get user info");
		return new Employee();
	}
	@Override
	public ArrayList<Employee> getAllEmpInfo() {
		ArrayList<Employee> allEmpl = new ArrayList<>();
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try {
			log.info("Retreiving all users info");
			String sql = "select * from empl_table where e_position = ?";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, "employee");
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next()) {
				allEmpl.add(new Employee(
						rs.getInt("E_ID"), 
						rs.getInt("E_JOB_ID"), 
						rs.getString("E_POSITION"), 
						rs.getString("E_FIRSTNAME"), 
						rs.getString("E_LASTNAME"), 
						rs.getString("E_EMAIL"), 
						rs.getString("E_USERNAME"), 
						rs.getString("e_password"))
						);
			} 
			return allEmpl;
		} catch (SQLException s) {
			log.error("Exception in getAllUsers thrown");
			s.getMessage();
		} finally {
			log.warn("getAllUsers - executed finally block");
		}log.warn("Failed to get all users info");
		return allEmpl;
	}
	@Override
	public ArrayList<Reimbursement> getResolvingMngr() {
		ArrayList<Reimbursement> resolvedList = new ArrayList<>();
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try {
			log.info("Retreiving user info");
			String sql = "SELECT et.e_job_id, et.e_firstname, et.e_lastname, et.e_email, r.r_id as rt, r.r_type, r.r_cost, r.r_status, "
					+ "mt.e_firstname as m_firstname, mt.e_lastname as m_lastname FROM reimb_table r LEFT JOIN empl_table et ON et.e_id = r.e_id "
					+ "left join empl_table mt on mt.e_id = r.m_id where r_status != ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "pending");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				log.info("did the while loop execute?");
				resolvedList.add(
						new Reimbursement(
								rs.getInt("e_job_id"),
								rs.getString("e_firstname"),
								rs.getString("e_lastname"),
								rs.getString("e_email"),
								rs.getInt("rt"),
								rs.getString("r_type"),
								rs.getDouble("r_cost"),
								rs.getString("r_status"),
								rs.getString("m_firstname"),
								rs.getString("m_lastname"))
					);
			}
			return resolvedList;
		} catch (SQLException s) {
			log.error("Exception in getUser thrown");
			s.getMessage();
		} finally {
			log.warn("getUser - executed finally block");
		}
		log.warn("Failed to get user info");
		return resolvedList;
	}
	@Override
	public boolean resolveReimbRqst(Employee employee, Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		log.info("Submitting reimbursement request into database");
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try /*(Connection conn = ConnectionUtil.getConnection())*/ {
			String storeProcs = "UPDATE reimb_table SET r_status = ?, m_id = ? WHERE r_id = ?";
			CallableStatement cs = conn.prepareCall(storeProcs);
//firstName varchar2, lastName varchar2, eMail varchar2, userName varchar2, pWord varchar2, workID number
			cs.setString(1, reimbursement.getReimbStatus());
			cs.setInt(2, employee.getEmplID());
			cs.setInt(3, reimbursement.getReimbID());
			
			//cs.executeUpdate();
			
			if(cs.executeUpdate() > 0) {
				log.info("Insert into database successful");
				return true;
			} 
		} catch (SQLException s) {
			log.error("Exception in insertUserProcedure thrown");
			s.getMessage();
			s.printStackTrace();
		} finally {
			log.warn("insertUserProcedure - executed finally block");
		}log.warn("Insert failed");
		return false;
	}
	@Override
	public ArrayList<Reimbursement> getPendingReimb() {
		ArrayList<Reimbursement> reimbList = new ArrayList<>();
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try {
			log.info("Retreiving user info");
			String sql = "SELECT * FROM REIMB_TABLE where R_STATUS = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "pending");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				log.info("did the while loop execute?");
				reimbList.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getInt("E_ID"),
						rs.getString("R_TYPE"),
						rs.getDouble("R_COST"),
						rs.getString("R_STATUS"),
						rs.getInt("M_ID")) 
						);
			}
			return reimbList;
		} catch (SQLException s) {
			log.error("Exception in getUser thrown");
			s.getMessage();
		} finally {
			log.warn("getUser - executed finally block");
		}
		log.warn("Failed to get user info");
		return reimbList;
	}
	
	public ArrayList<Reimbursement> getEmpRequest(Employee employee) {
		ArrayList<Reimbursement> reimbList = new ArrayList<>();
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try {
			log.info("Retreiving user info");
			String sql = "select et.e_job_id, rt.r_id, rt.r_type, rt.r_cost, rt.r_status "
					+ "from empl_table et left join reimb_table rt "
					+ "on rt.e_id = et.e_id where et.e_job_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee.getJobID());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				log.info("did the while loop execute?");
				reimbList.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getString("R_TYPE"),
						rs.getDouble("R_COST"),
						rs.getString("R_STATUS")
						));
			}
			return reimbList;
		} catch (SQLException s) {
			log.error("Exception in getUser thrown");
			s.getMessage();
		} finally {
			log.warn("getUser - executed finally block");
		}
		log.warn("Failed to get user info");
		return reimbList;
	}
	
}
