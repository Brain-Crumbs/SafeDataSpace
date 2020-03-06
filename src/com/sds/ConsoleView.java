package com.sds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ConsoleView {
	
	public static MenuOption displayMainMenu() {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("-------Welcome to SafeDataSpace.com BackOffice-------\n");
		System.out.println("Display All Contracts:     Enter 0");
		System.out.println("Display Closed Contracts:  Enter 1");
		System.out.println("Seach Contracts:           Enter 2");
		System.out.println("View Server Analytics:     Enter 3");
		System.out.println("Add a new Contract:        Enter 4");
		System.out.println("Quit:                      Enter Q");
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
		case "3": return MenuOption.DISPLAY_STATUS;
		case "4": return MenuOption.ADD;
		case "q": return MenuOption.QUIT;
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
	public static void quit() {
		System.out.println("Good Bye");
		System.exit(0);
	}

}
