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
// ******************************* MAIN *********************************************
	public static void main(String[] args) {
		Manager mngr = new Manager();
//		for (String str: getMngrDao().getAllEmpInfo()) {
//    		System.out.print(str);
//    	}
//		mngr.setUsrName("jt");
//		System.out.println(getMngrDao().doLogin(mngr.getUsrName()));
//		System.out.println();
		for (String str: getMngrDao().getAllEmpInfo()) {
    		System.out.print(str);
    	}
//		System.out.println(getMngrDao().getEmpInfo(3000));
//		System.out.println();
//		for (String str: getMngrDao().getPendingReimb()) {
//    		System.out.print(str);
//    	}
//		for (String str: getMngrDao().getResolvingMngr()) {
//    		System.out.print(str);
//    	}
	}
	@Override
	public boolean doLogin(String username) {
		String usrStr = "";
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try { 
			log.info("Manager attempted to login, check if user exists in database.");
			String sql = "SELECT * FROM mngr_table WHERE m_USERNAME = ?";
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
	public List<String> getAllEmpInfo() {
		List<String> allEmpl = new ArrayList<>();
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try {
			log.info("Retreiving all users info");
			String sql = "select * from empl_table";
			PreparedStatement ptmt = conn.prepareStatement(sql);
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
		List<String> resolvedList = new ArrayList<>();
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try {
			log.info("Retreiving user info");
			String sql = "SELECT m_firstname, m_lastname, e_job_id, e_firstname, e_lastname, e_email, r_id, r_type, r_cost, r_status " + 
					"FROM reimb_table " + 
					"LEFT JOIN mngr_table ON reimb_table.m_id = mngr_table.m_id " + 
					"left join empl_table on reimb_table.e_id = empl_table.e_id " + 
					"where r_status = 'approved' order by r_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				log.info("did the while loop execute?");
				resolvedList.add(
						new Manager(
								rs.getString("m_firstname"),
								rs.getString("m_lastname")).toStringJoin() 
						+ new Employee(
								rs.getInt("e_job_id"),
								rs.getString("e_firstname"),
								rs.getString("e_lastname"),
								rs.getString("e_email")).toStringJoin()
						+ new Reimbursement(
								rs.getInt("r_id"),
								rs.getString("r_type"),
								rs.getInt("r_cost"),
								rs.getString("r_status")).toStringJoin()
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
	public void resolveReimbRqst() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<String> getPendingReimb() {
		List<String> reimbList = new ArrayList<>();
		
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


