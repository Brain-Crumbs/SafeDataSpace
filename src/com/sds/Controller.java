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
		
<<<<<<< HEAD
		// Loop in MAIN MENU until QUIT
		while (true) {
			

			MenuOption mo = view.displayMainMenu();
			
			if (mo == MenuOption.DISPLAY_ALL) view.displayAllContacts(); ;
			if (mo == MenuOption.DISPLAY_CLOSED) view.displayClosedContacts();
			if (mo == MenuOption.SEARCH) subMenuSearch();
			if (mo == MenuOption.DISPLAY_STATS);
			if (mo == MenuOption.ADD)dbHandler.addNewContract();
			if (mo == MenuOption.QUIT) view.quit();
			
		}
=======
>>>>>>> refs/heads/master

	}
	
	public void mainMenu() {
		while (true) {
			
			MenuOption mo = view.displayMainMenu();
			
			if (mo == MenuOption.SEARCH) subMenuSearch();
			if (mo == MenuOption.DISPLAY_STATS) subMenuAnalytics();
			if (mo == MenuOption.ADD) dbHandler.addNewContract();
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
			
<<<<<<< HEAD
			if (mo == MenuOption.EDIT) dbHandler.searchForContract();
			if (mo == MenuOption.DISPLAY_FILES);
			if (mo == MenuOption.ADD_FILE) dbHandler.addFilesToDatabase(view.getUserFile());
			if (mo == MenuOption.DELETE)dbHandler.deleteContract();
=======
			if (mo == MenuOption.EDIT);
			if (mo == MenuOption.DISPLAY_FILES) view.displayContractFiles(dbHandler.getContractFiles(contract.getID()));
			if (mo == MenuOption.ADD_FILE) dbHandler.addFilesToDatabase(view.getUserFile(contract.getID()));
			if (mo == MenuOption.DELETE);
>>>>>>> refs/heads/master
			if (mo == MenuOption.RETURN) break;
			
		}
	}
	
	private void subMenuAnalytics() {
		
		// Loop in submenu until RETRUN -> goto MAIN MENU
		
		while (true) {
			
			MenuOption mo = view.displaySubMenuAnalytics();
			
			if (mo == MenuOption.DISPLAY_STATS) view.displaySpaceData(dbHandler.getSpaceData());;
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


