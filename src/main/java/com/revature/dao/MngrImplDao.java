package com.revature.dao;

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
	private static MngrImplDao mngrDao;
	final static Logger log = Logger.getLogger(MngrImplDao.class);
	
	private MngrImplDao() {}
	public static MngrImplDao getMngrDao(){
		if (mngrDao == null){
			mngrDao = new MngrImplDao();
		}
		return mngrDao;
	}
	@Override
	public boolean doLogin(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doLogout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmpInfo(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllEmpInfo() {
		List<String> allEmpl = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("Retreiving all users info");
			String sql = "select * from empl_table";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next()) {
				allEmpl.add(new Employee(//int mngrID, int jobID, String jobDescr, String firName, String lasName, String emailAddr,	String passWord
						rs.getInt("E_ID"), 
						rs.getInt("E_JOB_ID"), 
						rs.getString("E_POSITION"), 
						rs.getString("E_FIRSTNAME"), 
						rs.getString("E_LASTNAME"), 
						rs.getString("E_EMAIL"), 
						rs.getString("E_USERNAME"), 
						rs.getString("E_PWORD")).toString());
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
	public List<String> getResolvingMngr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resolveReimbRqst() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getPendingReimb(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		for (String str: getMngrDao().getAllEmpInfo()) {
    		System.out.print(str);
    	}
	}
	
	
	
	
	/*public List<String> getReimbStatus(Employee employee) {
		List<String> reimbList = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("Retreiving user info");
			//String sql = "SELECT * FROM reimb_table WHERE e_id = ? and r_status = ?";
			String sql = "SELECT * FROM mngr_table LEFT JOIN reimb_table ON mngr_table.m_id = reimb_table.m_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee.getEmplID());
			pstmt.setString(2, "pending");
			
			ResultSet rs = pstmt.executeQuery();
			//int reimbID, int emplID, String reimbType, int reimbCost, String reimbStatus, int apprMngr
			while (rs.next()) {
				log.info("did the while loop execute?");
				reimbList.add(new Reimbursement(
						rs.getInt("r_id"),
						rs.getInt("e_id"),
						rs.getString("r_type"),
						rs.getInt("r_cost"),
						rs.getString("r_status"),
						rs.getInt("m_id")).toString() 
						+ new Manager( //int mngrID, int jobID, String jobDescr, String firName, String lasName, String emailAddr, String passWord
								rs.getInt(""),
								rs.getInt(""),
								rs.getString(""),
								rs.getString(""),
								rs.getString(""),
								rs.getString(""),
								rs.getString("")));
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
	}*/
}


