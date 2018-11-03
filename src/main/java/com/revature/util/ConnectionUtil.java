package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionUtil {
	
//	public static Connection getConnection() throws SQLException{
//		String url="jdbc:oracle:thin:@revaturetrainingdatabase.c4svrsh9eep5.us-east-2.rds.amazonaws.com:1521:ORCL";	
//		String user = "ERS_ADMIN";
//		String pass = "ersadmin0000";
//		return DriverManager.getConnection(url, user, pass);
//	}
//}

	
	private static ConnectionUtil cu = null;
	private static Properties prop = new Properties();
	
	private ConnectionUtil() {
		super();
		InputStream dbProps = ConnectionUtil.class.getClassLoader()
				.getResourceAsStream("database.properties");
		try {
			prop.load(dbProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ConnectionUtil getInstance() {
		if(cu==null)
			cu=new ConnectionUtil();
		return cu;
	}
	public Connection getConnection() /*throws SQLException*/ {
		Connection conn = null;
		try {
			// We have to register the driver class
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
