package com.sds;

import java.awt.List;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import javax.swing.ListSelectionModel;

public class DBHandler {
	
	private static final String GET_ALL_CONTRACTS = "select * from contract";
	
	private static final String URL = "jdbc:mysql://localhost:3306/safedatabase";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	private Connection con;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public DBHandler() throws SQLException {
		this.con = DriverManager.getConnection(URL,USER,PASS);
	}
	
	ArrayList<String> contractList = new ArrayList<>();
	String contractString;
	public void GetFieldAllContracts() throws SQLException 
	{
		pst = con.prepareStatement(GET_ALL_CONTRACTS);
		rs = pst.executeQuery(GET_ALL_CONTRACTS);
		
		while (rs.next())
		{
			String name = rs.getString("name");
			int amountUsed = rs.getInt("amountUsed");
			boolean isActive = rs.getBoolean("isActive");
			String amountString = Integer.toString(amountUsed);
			String isActiveString = Boolean.toString(isActive);
			contractString = name + " " + amountString + " " + isActiveString;
			contractList.add(contractString);
			Object[] contractStrings = contractList.toArray();

		}
		
	}
	
	public long[] getSpaceData() {
		long[] spaceData = {0, 0, 0};
		return spaceData;
	}
	
	

}
