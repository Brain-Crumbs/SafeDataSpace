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
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.swing.ListSelectionModel;

public class DBHandler {
	
	private static final String GET_ALL_CONTRACTS = "select * from contract";
	private static final String INSERT_INTO_FILES = "Insert into files values (fileid, ?, ?)";
	private static final String GET_ALL_FILES = "select * from fileid";
	private static final String UPDATE_CONTRACT_TOTAL_SIZE = "Update contract set amountUsed = amountUsed + ? where contractID  = ?";
	
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
	//+ ConsoleView.contractID + ConsoleView.size + ConsoleView.path + 
	public void addFilesToDatabase(int[] fileData){
		int contractID = fileData[0];
		int size = fileData[1];
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(INSERT_INTO_FILES);
			ps.setInt(1, contractID);
			ps.setInt(2, size);
			ps.execute();
			
			ps = con.prepareStatement(UPDATE_CONTRACT_TOTAL_SIZE);
			ps.setInt(1, size);
			ps.setInt(2, contractID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
