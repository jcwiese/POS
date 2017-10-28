package com.pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection 
{
	protected Connection openSQL()
	{
		try {
		String server = "jdbc:sqlserver://localhost\\POS;databaseName=POS;user=sa;password=cmsc495;";
		return (DriverManager.getConnection(server));}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
