package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {

	public static Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	public static Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/";
		String username = "postgres";
		String password = "password";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			logger.warn("Unable to get connection to database");
		}
		
		try {
			conn.setSchema("Project1");
		} catch (SQLException e) {
			logger.warn("Unable to set Schema to project0");
		}
		
		return conn;
	}

}