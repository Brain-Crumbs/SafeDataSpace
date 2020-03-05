package com.sds;

import java.sql.SQLException;

public class Controller {
	
	
	public static void main(String[] args) {
		try
		{
			DBHandler dbHandler = new DBHandler();
			dbHandler.GetFieldAllContracts();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Loop in MAIN MENU until QUIT
		while (true) {
			
			MenuOption mo = ConsoleView.displayMainMenu();
			
			if (mo == MenuOption.DISPLAY_ALL);
			if (mo == MenuOption.DISPLAY_CLOSED);
			if (mo == MenuOption.SEARCH) subMenuSearch();
			if (mo == MenuOption.DISPLAY_STATUS);
			if (mo == MenuOption.ADD);
			if (mo == MenuOption.QUIT) ConsoleView.quit();
			
		}
		
	}
	
	private static void subMenuSearch() {
		
		// Loop in submenu until RETURN -> goto MAIN MENU
		while (true) {
			
			break;
			
		}
	}
	
	private static void subMenuSearchBy() {
		
		//subMenu of Search, loop unitl Return -> goto SEARCH
		while (true) {
			
			break;
		}
	}
	
	private static void subMenuAnalytics() {
		
		// Loop in submenu until RETRUN -> goto MAIN MENU
		
		while (true) {
			
			break;
			
		}
		
	}
}


