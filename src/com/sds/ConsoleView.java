package com.sds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

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
		case "r": return MenuOption.RETURN;
		default: return MenuOption.ERROR;
		}
		
	}
	static void displayAllContacts() 
	{
		try
		{
			String [][] contactStrings;
			DBHandler handler = new DBHandler();
			contactStrings = handler.GetFieldAllContracts();
			System.out.println("-----Contacts-----");
			System.out.println("Name\t  Space used\t\tAcvtive");
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
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	static void displayClosedContacts()  
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
	
	}
	public MenuOption displaySubMenuSearchBy() {
		
		System.out.println("-------Currently Viewing (contract name / ID)-------\n");
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
	
	public void quit() {
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Good Bye");
		System.exit(0);
	}
	


}
