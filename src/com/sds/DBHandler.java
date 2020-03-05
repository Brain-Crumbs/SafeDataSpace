package com.sds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler {
	
	private static final String GET_ALL_CONTRACTS = "select * from contracts";
	
	private static final String URL = "jdbc:mysql://localhost:3306/safedataspace";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	private Connection con;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public DBHandler() throws SQLException {
		this.con = DriverManager.getConnection(URL,USER,PASS);
	}
	
	
	

}
