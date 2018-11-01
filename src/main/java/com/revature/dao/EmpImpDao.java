package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmpImpDao implements EmpDao{
	private static EmpImpDao emplDao;
	final static Logger log = Logger.getLogger(EmpImpDao.class);
	
	private EmpImpDao() {
	}
	public static EmpImpDao getImpDao(){
		if (emplDao == null){
			emplDao = new EmpImpDao();
		}
		return emplDao;
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
	public void postReimbRqst() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getReimbStatus() {
		// TODO Auto-generated method stub
		
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

}
