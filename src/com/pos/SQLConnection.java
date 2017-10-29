package com.pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection 
{
	protected Connection openSQL()
	{
		try {
		//String server = "jdbc:sqlserver://localhost\\POS;databaseName=POS;user=sa;password=cmsc495;";
		String server = "jdbc:sqlserver://poscmsc495.database.windows.net:1433;database=POS;user=wholl@poscmsc495;password=cmsc495!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		return (DriverManager.getConnection(server));}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
