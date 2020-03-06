package com.sds;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDBHandler {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	void test_contacts_array() 
	{
		try
		{
			DBHandler handler = new DBHandler();
			handler.GetFieldAllContracts();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void test_two_dimension_array() 
	{
		try
		{
			String [][] contactStrings;
			DBHandler handler = new DBHandler();
			contactStrings = handler.GetFieldAllContracts();
			System.out.println("-----Contacts-----");

			for (int i = 0; i < contactStrings.length; i++)
			{
				for (int j = 0; j < contactStrings[i].length; j++)
				{
					
					System.out.print(contactStrings[i][j] +" ");
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

}
