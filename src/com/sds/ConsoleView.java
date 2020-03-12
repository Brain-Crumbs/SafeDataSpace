package com.sds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ConsoleView {
	
	private BufferedReader in;
	
	
	public ConsoleView() {
		this.in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public MenuOption displayMainMenu() {
		
		
		System.out.println("-------Welcome to SafeDataSpace.com BackOffice-------\n");
		System.out.println("Seach Contracts:           Enter 1");
		System.out.println("View Server Analytics:     Enter 2");
		System.out.println("Add a new Contract:        Enter 3");
		System.out.println("Quit:                      Enter Q");
		System.out.println();
		
		String input = "";
		try {
			input = in.readLine().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
		switch(input) {
		case "1": return MenuOption.SEARCH;
		case "2": return MenuOption.DISPLAY_STATS;
		case "3": return MenuOption.ADD;
		case "q": return MenuOption.QUIT;
		default: return MenuOption.ERROR;
		}
		
	}
	
	public MenuOption displaySubMenuSearch() {
		
		System.out.println("-------Search Contracts-------\n");
		System.out.println("Display All:        Enter 0");
		System.out.println("Display Closed:     Enter 1");
		System.out.println("Search by Name/ID:  Enter 2");
		System.out.println("Return:             Enter R");
		System.out.println();
		
		String input = "";
		try {
			input = in.readLine().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(input) {
		case "0": return MenuOption.DISPLAY_ALL;
		case "1": return MenuOption.DISPLAY_CLOSED;
		case "2": return MenuOption.SEARCH;
		case "3": return MenuOption.DISPLAY_STATS;
		case "4": return MenuOption.ADD;
		case "q": return MenuOption.QUIT;
		case "r": return MenuOption.RETURN;
		default: return MenuOption.ERROR;
		}
		
	
		}
	
	public MenuOption displaySubMenuSearchBy(Contract contract) {
		
		if (contract.getName() == null || contract.getID() == -1) {
			System.out.println("Contract was not Found");
			return MenuOption.RETURN;
		} 
		
		System.out.println("-------Currently Viewing " + contract.getName()  + "/" + contract.getID()
				+ "-------\n");
		
		System.out.println("Edit Contract:  Enter 0");
		System.out.println("View Files:     Enter 1");
		System.out.println("Add file:       Enter 2");
		System.out.println("Delete file:    Enter 3");
		System.out.println("Return:         Enter R");
		System.out.println();
		
		String input = "";
		try {
			input = in.readLine().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch(input) {
		case "0": return MenuOption.EDIT;
		case "1": return MenuOption.DISPLAY_FILES;
		case "2": return MenuOption.ADD_FILE;
		case "3": return MenuOption.DELETE;
		case "r": return MenuOption.RETURN;
		default: return MenuOption.ERROR;
		}
		
	}
	
	public MenuOption displaySubMenuAnalytics() {
		
		System.out.println("-------Accounting and Analytics-------\n");
		System.out.println("Display Server Stats:  Enter 0");
		System.out.println("Display Accoutning:    Enter 1");
		System.out.println("Return:                Enter R");
		System.out.println();
		
		String input = "";
		try {
			input = in.readLine().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(input) {
		case "0": return MenuOption.DISPLAY_STATS;
		case "1": return MenuOption.DISPLAY_REVENUE;
		case "r": return MenuOption.RETURN;
		default: return MenuOption.ERROR;
		}
		
	}
	
	public int[] getUserFile(int contractID) {
		String path;
		int size;
		
		while (true) {
		try {
			System.out.println("Please input the file path:");
			path = in.readLine(); // adding file path has no functionality for this project
			System.out.println("Please input file size:");
			size = Integer.parseInt(in.readLine());
			
			int[] output = {contractID, size};
			return output;
		
		
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		
		}
	}
	
	private static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public Contract setContract(Contract contract) {
		DBHandler db;
		try {
			db = new DBHandler();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return contract;
		}
		
		System.out.println("\nPlease Enter Contract Name or ID to search:\n");
		
		String input = "";
		try {
			input = in.readLine().toLowerCase();
			if (isInteger(input)) {
				contract.setID(Integer.parseInt(input));
				contract.setName(db.searchContract(contract.getID()));
			} else {
				contract.setName(input);
				contract.setID(db.searchContract(input));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		clearConsole();
		return contract;
		
	}
	
	public void displayAllContracts() 
	{
		try
		{
			String [][] contactStrings;
			DBHandler handler = new DBHandler();
			contactStrings = handler.GetFieldAllContracts();
			System.out.println("-----Contacts-----");
			System.out.println("Name\t  Space used\t\tActive");
			for (int i = 0; i < contactStrings.length; i++)
			{
				for (int j = 0; j < contactStrings[i].length; j++)
				{
					
					System.out.print(contactStrings[i][j] +"\t\t");
					if (j==2)
					{
						System.out.println();
					}
					
				}
			}
			
			System.out.println();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForUser();
	}
	
	public void displayClosedContacts()  
	{
		String[][] contactStrings;
		DBHandler handler;
		try
		{
			handler = new DBHandler();
			contactStrings = handler.GetFieldAllContracts();
			System.out.println("----Inactive Contracts----");
			for (int i = 0; i <= contactStrings.length-1; i++)
			{
				for (int j = 0; j <=contactStrings.length -1; j++)
				{
					if (Boolean.parseBoolean(contactStrings[i][2]) == false)
					{
						System.out.print(contactStrings[i][j] + " ");
					}
				}
			}
			System.out.println("");

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForUser();
	}

	public void displayContractFiles(Object[][] files) {
	
		System.out.println("------------FILES CURRENTLY IN SYSTEM--------");
		for (int r = 0; r < files.length; r++) {
			if (files[r][0] != null && files[r][2] != null) {
				System.out.print("File ID: " + files[r][0] + "\tSize: " + files[r][2] + " bytes");
			System.out.println();
			}
			
		}
		System.out.println();
		waitForUser();
		
	}
	
	public void displaySpaceData(long[] spaceData) {
		
		System.out.println("\nTotal Space:     " + spaceData[0] + " bytes");
		System.out.println("Space In Use:    " + spaceData[1] + " bytes");
		System.out.println("Available Space: " + spaceData[2] + " bytes\n");
		
		waitForUser();
	}
	
	public void waitForUser() {
		System.out.println();
		System.out.println("Press Any Key To Continue");
		try {
			in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clearConsole();
		
	}
	
	public void clearConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	public void quit() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Good Bye");
		System.exit(0);
	}

}
 