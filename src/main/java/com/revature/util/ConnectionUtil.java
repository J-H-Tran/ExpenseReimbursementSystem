package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException{
		String url="jdbc:oracle:thin:@revaturetrainingdatabase.c4svrsh9eep5.us-east-2.rds.amazonaws.com:1521:ORCL";	
		String user = "ERS_ADMIN";
		String pass = "ersadmin0000";
		return DriverManager.getConnection(url, user, pass);
	}
}
