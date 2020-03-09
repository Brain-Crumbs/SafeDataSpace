package com.sds;

import java.sql.SQLException;

public class Controller {
	
	ConsoleView view;
	DBHandler dbHandler;
	
	@SuppressWarnings("static-access")
	public Controller() {
		this.view = new ConsoleView();
		try {
			this.dbHandler = new DBHandler();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Loop in MAIN MENU until QUIT
		while (true) {
			

			MenuOption mo = view.displayMainMenu();
			
			if (mo == MenuOption.DISPLAY_ALL) view.displayAllContacts(); ;
			if (mo == MenuOption.DISPLAY_CLOSED) view.displayClosedContacts();
			if (mo == MenuOption.SEARCH) subMenuSearch();
			if (mo == MenuOption.DISPLAY_STATS);
			if (mo == MenuOption.ADD);
			if (mo == MenuOption.QUIT) view.quit();
			
		}

	}
	
	public void mainMenu() {
		while (true) {
			
			MenuOption mo = view.displayMainMenu();
			
			if (mo == MenuOption.SEARCH) subMenuSearch();
			if (mo == MenuOption.DISPLAY_STATS) subMenuAnalytics();
			if (mo == MenuOption.ADD);
			if (mo == MenuOption.QUIT) view.quit();
			
		}
	}
	
	
	private void subMenuSearch() {
		
		// Loop in submenu until RETURN -> goto MAIN MENU
		while (true) {
			
			MenuOption mo = view.displaySubMenuSearch();
			
			if (mo == MenuOption.DISPLAY_ALL)view.displayAllContacts();;
			if (mo == MenuOption.DISPLAY_CLOSED)view.displayClosedContacts();;
			if (mo == MenuOption.SEARCH) subMenuSearchBy();
			if (mo == MenuOption.RETURN) break;
			
		}
	}
	
	private void subMenuSearchBy() {
		
		//subMenu of Search, loop unitl Return -> goto SEARCH
		while (true) {
			
			MenuOption mo = view.displaySubMenuSearchBy();
			
			if (mo == MenuOption.EDIT);
			if (mo == MenuOption.DISPLAY_FILES);
			if (mo == MenuOption.ADD_FILE) dbHandler.addFilesToDatabase(view.getUserFile());
			if (mo == MenuOption.DELETE);
			if (mo == MenuOption.RETURN) break;
			
		}
	}
	
	private void subMenuAnalytics() {
		
		// Loop in submenu until RETRUN -> goto MAIN MENU
		
		while (true) {
			
			MenuOption mo = view.displaySubMenuAnalytics();
			
			if (mo == MenuOption.EDIT);
			if (mo == MenuOption.DISPLAY_FILES);
			if (mo == MenuOption.ADD_FILE);
			if (mo == MenuOption.DELETE);
			if (mo == MenuOption.RETURN) break;
			
		}
		
	}
	
	public static void main(String[] args) {

		// Loop in MAIN MENU until QUIT
		Controller c = new Controller();
		c.mainMenu();
	}
}


