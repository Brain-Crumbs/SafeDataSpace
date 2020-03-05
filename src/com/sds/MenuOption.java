package com.sds;

public enum MenuOption {
	
	DISPLAY_ALL("Display All"),
	DISPLAY_CLOSED("Display All Closed Contracts"),
	SEARCH("Search by Name or ID"),
	EDIT("Edit"),
	ADD("Add"),
	DELETE("Delete"),
	DISPLAY_STATUS("Display Server Stats"),
	DISPLAY_REVENUE("Display Accounting Info"),
	ERROR("Error"),
	RETURN("Return to last Menu"),
	QUIT("Exit the application");
	
	private final String moString;

	MenuOption(String string) {
		this.moString = string;
	}
	
	public String toString() {
		return this.moString;
	}

}
