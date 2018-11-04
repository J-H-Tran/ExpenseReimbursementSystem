package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Login;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class LoginOracle implements LoginDao {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public Employee login(String username, String password) {

		Connection conn = null;
		conn = cu.getConnection();
		String sql = "Select * from empl_table "
				+ "where e_username = ? and e_password =?";
		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Employee(
						rs.getInt("E_ID"), 
						rs.getInt("E_JOB_ID"), 
						rs.getString("E_POSITION"), 
						rs.getString("E_FIRSTNAME"), 
						rs.getString("E_LASTNAME"), 
						rs.getString("E_EMAIL"), 
						rs.getString("E_USERNAME"), 
						rs.getString("e_password")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
