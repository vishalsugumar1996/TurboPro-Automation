package com.sysvine.turbopro.automation.utils;

import java.sql.*;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class DBConnector {
	public static Connection connection;

	@BeforeSuite
	public void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionURL = "jdbc:mysql://sysvines002:3306/BartosProd20170823";
			String userName = "turbo_qe";
			String password = "vine@2019";
			connection = DriverManager.getConnection(connectionURL, userName, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@AfterSuite
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
