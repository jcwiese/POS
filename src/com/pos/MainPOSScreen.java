package com.pos;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.*;

import javax.swing.*;

public class MainPOSScreen 
{
	private JFrame POSframe;
	private int UserID;
	private String Fname,Lname;
	
	public MainPOSScreen(int id)
	{
		UserID = id;
		Connection con = new SQLConnection().openSQL();
		String stmt = "Select FirstName,LastName from USERS WHERE UserID = " + UserID;
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(stmt);
			r.next();
			Fname = r.getString("FirstName");
			Lname = r.getString("LastName");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		initialize();
	}

	
	private void initialize() 
	{
		POSframe = new JFrame();
		POSframe.getContentPane().setBackground(Color.WHITE);
		POSframe.setTitle("CMSC 451 POS  -- LOGGED IN AS: " + Fname + " "+ Lname);
		POSframe.setBounds(100, 100, 915, 663);
		POSframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		POSframe.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		POSframe.getContentPane().add(panel);
		
		
		
		
		POSframe.setVisible(true);
	}
}
