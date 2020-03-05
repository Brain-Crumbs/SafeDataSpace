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
		
	}
}
