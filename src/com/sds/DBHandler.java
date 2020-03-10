package com.sds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import javax.swing.ListSelectionModel;



public class DBHandler {
	
	private static final String GET_ALL_CONTRACTS = "select * from contract";
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
	
	public void searchForContract() 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter contract name to search for:");
		String searchByName = scan.nextLine();
		try
		{
			searchByName = SEARCH_CONTRACT + "'" + searchByName + "'";
			pst = con.prepareStatement(searchByName);
			rs = pst.executeQuery(searchByName);
			System.out.println("Name\tContract ID");
			while (rs.next())
			{
				searchByName = rs.getString("name");
				searchByName += "\t";
				searchByName += rs.getString("contractID");
				System.out.println(searchByName);
			}
			System.out.println("__________________");

			
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
	}
	
	public void deleteContract() 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter name of contract to delete");
		String contractToDelete = scan.nextLine();
		System.out.println(DELETE_CONTRACT +"'" + contractToDelete+ "';" );

		System.out.println("are you sure you want to delete"  + contractToDelete);
		String confrirmDelete = scan.next();
		if (confrirmDelete.equalsIgnoreCase("y") || confrirmDelete.equalsIgnoreCase("yes"))
		{
		try
		{
		String deleteString = (DELETE_CONTRACT +"'" + contractToDelete+ "';");
		pst = con.prepareStatement(deleteString);
			pst.executeUpdate(deleteString);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(contractToDelete + "'s contract is deleted");
		} else {
			System.out.println("Contract does not exsist");
		}
		
		
		
	}
	
	String[][] contractArray;
	ArrayList<String> contractList = new ArrayList<>();
	String contractString;
	public String[][] GetFieldAllContracts() throws SQLException 
	{
		pst = con.prepareStatement(GET_ALL_CONTRACTS);
		rs = pst.executeQuery(GET_ALL_CONTRACTS);
		int count = 0;
		rs.last();
		int rowCount = rs.getRow();
		contractArray = new String[rowCount -1][3];
		rs.first();
		while (rs.next())
		{
			rowCount++;
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
				rowCount +=1;
				//System.out.println(count);
			
			 
			contractString = name + " " + amountString + " " + isActiveString;
			contractList.add(contractString);
			Object[] contractStrings = contractList.toArray();
		}
		 return contractArray;

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

	
	void addNewContract () 
	{
		Scanner scan = new Scanner(System.in);
		Boolean activeAccount;
		System.out.println("Enter name for contract");
		String contractName = scan.nextLine();
		System.out.println("Enter GBs used");
		int GBsize = scan.nextInt();
		System.out.println("Is this contract active? Yes or no?");
		String isActive = scan.next();
		if (isActive.equalsIgnoreCase("y") || isActive.equalsIgnoreCase("yes"))
		{
			activeAccount = true;
		} else 
		{
			activeAccount = false;
		}
		
		PreparedStatement ps;
		try
		{
			int tierID = 3;
			String addToDB = (ADD_NEW_CONTACT_STRING + " (contractID, "+ tierID +", \"" +  contractName +"\""+","+ GBsize +","+ activeAccount +")");
			ps = con.prepareStatement(addToDB, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet tableKey = ps.getGeneratedKeys();
			tableKey.next();
			System.out.println("Contract added!");
			
		} catch (Exception e)
		{
			System.err.println("CONTRACT NOT ADDED");
			e.printStackTrace();
		}
	}
	
}
