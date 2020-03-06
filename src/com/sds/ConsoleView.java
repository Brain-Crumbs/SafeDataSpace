package com.sds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleView {
	static boolean createRecordFlag = false;
	static double size;
	static String contractID;
	static String path;
	
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
		case "4": createRecordFlag = true; return MenuOption.ADD;
		case "q": return MenuOption.QUIT;
		default: return MenuOption.ERROR;
		}
		
	
		}
	
	public static void getData() {
		if (createRecordFlag == true) {
			Scanner sc = new Scanner(System.in); 
			System.out.println("Please input the ContractID:");
			contractID = sc.nextLine();
			System.out.println("Please input the file path:");
			path = sc.nextLine();
			System.out.println("Please input file size:");
			size = Double.parseDouble(sc.nextLine());
		}
	}
	
	public static void quit() {
		System.out.println("Good Bye");
		System.exit(0);
	}

}
