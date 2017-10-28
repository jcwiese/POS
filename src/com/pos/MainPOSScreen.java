package com.pos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class MainPOSScreen 
{
	private JFrame POSframe = new JFrame();
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
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					initialize();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}});
	}

	
	private void initialize() 
	{
		POSframe.getContentPane().setBackground(Color.WHITE);
		POSframe.setTitle("CMSC 451 POS  -- LOGGED IN AS: " + Fname + " "+ Lname);
		POSframe.setBounds(100, 100, 915, 663);
		POSframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		POSframe.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		POSframe.getContentPane().add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		POSframe.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		 mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					System.exit(0);
					
				}
	        });
		
		JMenuItem mntmLogOut = new JMenuItem("LogOut");
		
		 mntmLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent f) 
				{
					logout();
				}
	        });
		mnFile.add(mntmLogOut);
		mnFile.add(mntmExit);
		
		//TODO add way to open AdminScreen but not close entire program when clicking exit.
		
		
		POSframe.setVisible(true);
	
	}

	protected void logout() 
	{
		this.POSframe.dispose();
		new LoginScreen();
	}
	
}
