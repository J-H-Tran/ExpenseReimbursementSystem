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
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class EmpImpDao implements EmpDao{
	private static EmpImpDao emplDao;
	final static Logger log = Logger.getLogger(EmpImpDao.class);
	
	private EmpImpDao() {}
	public static EmpImpDao getImpDao(){
		if (emplDao == null){
			emplDao = new EmpImpDao();
		}
		return emplDao;
	}
	public boolean verifyReimb() {
		String usrStr = "";
		try { 
			log.info("Checking success of reimbursement request");
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM REIMB_TABLE WHERE R_STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "pending");
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) { usrStr = rs.getString("R_STATUS");
			}
			if (usrStr.equals("pending")) {
				log.info("Reimbursement request sent.");
				return true;
			}
		}catch (Exception e) {
			log.error("Exception in verifyReimb thrown");
			e.printStackTrace();
		}log.warn("Reimbursement unsuccessful");
		return false;
	}
	@Override
	public boolean doLogin(String username) {
		String usrStr = "";
		try { 
			log.info("User attempted to login, check if user exists in database.");
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM EMPL_TABLE WHERE E_USERNAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) { usrStr = rs.getString("E_USERNAME");
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
	public boolean postReimbRqst(Employee employee, Reimbursement reimbursement) {
		log.info("Submitting reimbursement request into database");
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String storeProcs = "CALL ADD_REIMB(?, ?, ?)";
			CallableStatement cs = conn.prepareCall(storeProcs);
			
			cs.setInt(1, employee.getEmplID());
			cs.setString(2, reimbursement.getReimbType());
			cs.setInt(3, reimbursement.getReimbCost());
			
			cs.executeUpdate();
			
			if(verifyReimb()) {
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
	public List<String> getPendingReimb(Employee employee) {
		List<String> reimbList = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("Retreiving user info");
			String sql = "SELECT * FROM REIMB_TABLE WHERE E_ID = ? AND R_STATUS = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee.getEmplID());
			pstmt.setString(2, "pending");
			
			ResultSet rs = pstmt.executeQuery();
			//int reimbID, int emplID, String reimbType, int reimbCost, String reimbStatus, int apprMngr
			while (rs.next()) {
				log.info("did the while loop execute?");
				reimbList.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getInt("E_ID"),
						rs.getString("R_TYPE"),
						rs.getInt("R_COST"),
						rs.getString("R_STATUS"),
						rs.getInt("M_ID")).toString() 
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
	@Override
	public List<String> getApprovedReimb(Employee employee) {
		List<String> reimbList = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("Retreiving user info");
			String sql = "SELECT * FROM REIMB_TABLE WHERE E_ID = ? AND R_STATUS = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee.getEmplID());
			pstmt.setString(2, "approved");
			
			ResultSet rs = pstmt.executeQuery();
			//int reimbID, int emplID, String reimbType, int reimbCost, String reimbStatus, int apprMngr
			while (rs.next()) {
				log.info("did the while loop execute?");
				reimbList.add(new Reimbursement(
						rs.getInt("R_ID"),
						rs.getInt("E_ID"),
						rs.getString("R_TYPE"),
						rs.getInt("R_COST"),
						rs.getString("R_STATUS"),
						rs.getInt("M_ID")).toString() 
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
	@Override
	public Employee getEmpInfo(String username) {
		Employee empl = new Employee();
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("Retreiving user info");
			String sql = "SELECT * FROM EMPL_TABLE WHERE E_USERNAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
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
						rs.getString("E_PWORD"));
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
	public void updateEmpInfo() {
		// TODO Auto-generated method stub
		
	}
//	public static void main(String[] args) {
//		
//		Employee empl = new Employee();
//		empl.setEmplID(1);
//		System.out.println(getImpDao().getApprovedReimb(empl));
//	}
}
