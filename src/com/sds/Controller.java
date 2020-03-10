package com.sds;

import java.sql.SQLException;

public class Controller {
	
	private Contract contract;
	
	ConsoleView view;
	DBHandler dbHandler;
	
	public Controller() {
		this.view = new ConsoleView();
		try {
			this.dbHandler = new DBHandler();
		} catch (SQLException e) {
			e.printStackTrace();
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
			
			if (mo == MenuOption.DISPLAY_ALL)view.displayAllContracts();;
			if (mo == MenuOption.DISPLAY_CLOSED)view.displayClosedContacts();;
			if (mo == MenuOption.SEARCH) subMenuSearchBy();
			if (mo == MenuOption.RETURN) break;
			
		}
	}
	
	private void subMenuSearchBy() {
		
		contract = view.setContract(new Contract());
		
		//subMenu of Search, loop until Return -> goto SEARCH
		while (true) {
			
			MenuOption mo = view.displaySubMenuSearchBy(contract);
			
			if (mo == MenuOption.EDIT);
			if (mo == MenuOption.DISPLAY_FILES) view.displayContractFiles(dbHandler.getContractFiles(contract.getID()));
			if (mo == MenuOption.ADD_FILE) dbHandler.addFilesToDatabase(view.getUserFile(contract.getID()));
			if (mo == MenuOption.DELETE);
			if (mo == MenuOption.RETURN) break;
			
		}
	}
	
	private void subMenuAnalytics() {
		
		// Loop in submenu until RETRUN -> goto MAIN MENU
		
		while (true) {
			
			MenuOption mo = view.displaySubMenuAnalytics();
			
			if (mo == MenuOption.DISPLAY_STATS);
			if (mo == MenuOption.DISPLAY_REVENUE);
			if (mo == MenuOption.RETURN) break;
			if (mo == MenuOption.ERROR);
			
		}
		
	}
	
	public static void main(String[] args) {

		// Loop in MAIN MENU until QUIT
		Controller c = new Controller();
		c.mainMenu();
	}
}


