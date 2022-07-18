package com.projectone.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

public static Connection getNewConnection() throws SQLException {
		
		return DriverManager.getConnection(
				System.getenv("postgres_url"), 
				System.getenv("postgres_username"), 
				System.getenv("postgres_password")
			);
	}

}

