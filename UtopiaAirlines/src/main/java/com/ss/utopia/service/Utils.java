/**
 * 
 */
package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author derrianharris
 *
 */
public class Utils {

	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/utopia";
	private final String username = "root";
	private final String password = "DerrianHarris";

	public Connection getConnection()
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(false);
		return conn;
	}
}
