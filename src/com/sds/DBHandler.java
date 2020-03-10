package com.sds;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.swing.ListSelectionModel;



public class DBHandler {
	
	private static final String GET_ALL_CONTRACTS = "select * from contract";
	private static final String GET_ALL_FILES_WITH_ID = "select * from files where contractID = ?;";
	private static final String GET_SIZE = "SELECT Size FROM files";
	private static final String INSERT_INTO_FILES = "Insert into files values (fileid, ?, ?)";
	private static final String GET_ALL_FILES = "select * from fileid";
	private static final String UPDATE_CONTRACT_TOTAL_SIZE = "Update contract set amountUsed = amountUsed + ? where contractID  = ?";
	private static final String ADD_NEW_CONTACT_STRING = "insert into contract values";
	
	private static final String URL = "jdbc:mysql://localhost:3306/safedatabase";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	
	private Connection con;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public DBHandler() throws SQLException {
		this.con = DriverManager.getConnection(URL,USER,PASS);
	}
	String[][] contractArray = new String[3][3];
	ArrayList<String> contractList = new ArrayList<>();
	String contractString;
	public String[][] GetFieldAllContracts() throws SQLException 
	{
		pst = con.prepareStatement(GET_ALL_CONTRACTS);
		rs = pst.executeQuery(GET_ALL_CONTRACTS);
		int count = 0;
		while (rs.next())
		{
			String name = rs.getString("name");
			int amountUsed = rs.getInt("amountUsed");
			boolean isActive = rs.getBoolean("isActive");
			String amountString = Integer.toString(amountUsed);
			String isActiveString = Boolean.toString(isActive);
			 contractString = name + " " + amountString + " " + isActiveString;
			// contractList.add(contractString);
						 
				contractArray[count][0] = name;
				contractArray[count][1] = amountString;
				contractArray[count][2] = isActiveString;
				count+=1;
				//System.out.println(count);
			
			 
			contractString = name + " " + amountString + " " + isActiveString;
			contractList.add(contractString);
			Object[] contractStrings = contractList.toArray();

		}
		return contractArray;
	}
	
	public long[] getSpaceData() {
		try {
			long[] spaceData = {0, 0, 0};
			spaceData[0] = SafeDataSpace.getTotalSpace();

			pst = con.prepareStatement(GET_ALL_CONTRACTS);
			rs = pst.executeQuery(GET_ALL_CONTRACTS);
			long spaceUsed = 0;
			while(rs.next()) {
				int amountUsed = rs.getInt("amountUsed");
				spaceUsed += amountUsed;
			}
			
			spaceData[1] = spaceUsed;
			
			long spaceAvailable = SafeDataSpace.getTotalSpace() - spaceUsed;
			
			spaceData[2] = spaceAvailable;
			
			return spaceData;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	

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

	
	void addNewConttact () 
	{
		
	}
	
	public String searchContract(int cID) {
		try {
			pst = con.prepareStatement(GET_ALL_CONTRACTS);
			rs = pst.executeQuery(GET_ALL_CONTRACTS);
	
			while (rs.next()){
				if (rs.getInt("contractID") == cID) {
					return rs.getString("name").toLowerCase();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public int searchContract(String cName) {
		try {
			pst = con.prepareStatement(GET_ALL_CONTRACTS);
			rs = pst.executeQuery(GET_ALL_CONTRACTS);
			
			while (rs.next()){
				String tempName = rs.getString("name").toLowerCase();
				if (tempName.equals(cName)) {
					return rs.getInt("contractID");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	
	public Object[][] getContractFiles(int cID) {
		
		try {
			pst = con.prepareStatement(GET_ALL_FILES_WITH_ID);
			pst.setInt(1, cID);
			rs = pst.executeQuery();
			
			int cols = rs.getMetaData().getColumnCount();
			rs.last();
			int rows = rs.getRow();
			rs.first();
			
			Object[][] resultSet = new Object[rows][cols];
		        int row = 0;
		        while (rs.next()) {
		            for (int i = 0; i < cols; i++) {
		                resultSet[row][i] = rs.getObject(i+1);
		            }
		            row++;
		        }
		    return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
